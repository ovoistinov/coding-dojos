package com.ovoistinov.coding.dojos.dependency_tree;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.ovoistinov.coding.dojos.dependency_tree.BuildSequenceGeneratorBuilder.getBuildSequenceGenerator;
import static org.assertj.core.api.Assertions.assertThat;

public class DependencyTreeBuilderTest {
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void twoIndependentProjectsAreBuilt() {
        Map<String, Collection<String>> input = new HashMap();
        input.put("A", Arrays.asList("A"));
        input.put("B", Arrays.asList("B"));

        assertThat(getBuildSequenceGenerator().withInput(input).build().getBuildSequence()).isEqualTo("BA");
    }

    @Test
    public void twoDependentProjectsAreBuiltAsBA() {
        Map<String, Collection<String>> input = new HashMap();
        input.put("A", Arrays.asList("A", "B"));
        input.put("B", Arrays.asList("B"));

        assertThat(getBuildSequenceGenerator().withInput(input).build().getBuildSequence()).isEqualTo("BA");
    }

    @Test
    public void threeDependentProjectsAreBuiltAsCBA() {
        Map<String, Collection<String>> input = new HashMap();
        input.put("A", Arrays.asList("A", "B", "C"));
        input.put("B", Arrays.asList("B"));
        input.put("C", Arrays.asList("C"));

        assertThat(getBuildSequenceGenerator().withInput(input).build().getBuildSequence()).isEqualTo("CBA");
    }

    @Test
    public void generatesDependencyTreeForComplexProject() {
        Map<String, Collection<String>> input = new HashMap();
        input.put("A", Arrays.asList("A", "B", "C", "F"));
        input.put("B", Arrays.asList("B", "F"));
        input.put("C", Arrays.asList("C", "F"));
        input.put("D", Arrays.asList("D"));
        input.put("E", Arrays.asList("E", "A", "B"));
        input.put("F", Arrays.asList("F"));
        input.put("G", Arrays.asList("G", "D"));

        assertThat(getBuildSequenceGenerator().withInput(input).build().getBuildSequence()).isEqualTo("ABC");
    }

    @Test
    public void cyclicDependencyProducesError() {
        Map<String, Collection<String>> input = new HashMap();
        input.put("A", Arrays.asList("A", "B"));
        input.put("B", Arrays.asList("B", "C"));
        input.put("C", Arrays.asList("C", "A"));

        exception.expect(IllegalStateException.class);
        exception.expectMessage("Cyclic dependency detected");

        getBuildSequenceGenerator().withInput(input).build().getBuildSequence();
    }
}
