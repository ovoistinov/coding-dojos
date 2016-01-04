package com.ovoistinov.coding.dojos.graphtraversal;

import org.junit.Test;
import java.util.*;

import static com.ovoistinov.coding.dojos.graphtraversal.ClusterCounter.getClusterCounterBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class GraphTraversalTest {

    @Test
    public void findsThreeClustersForThreeSeparateNodes() {
        Map<Integer, Collection<Integer>> input = new HashMap();
        input.put(1, Arrays.asList(1));
        input.put(2, Arrays.asList(2));
        input.put(3, Arrays.asList(3));

        assertThat(getClusterCounterBuilder().withInput(input).build().countClusters()).isEqualTo(3);
    }

    @Test
    public void findsTwoClustersForThreeNodes() {
        Map<Integer, Collection<Integer>> input = new HashMap();
        input.put(1, Arrays.asList(1,2));
        input.put(2, Arrays.asList(2,1));
        input.put(3, Arrays.asList(3));

        assertThat(getClusterCounterBuilder().withInput(input).build().countClusters()).isEqualTo(2);
    }

    @Test
    public void findsThreeClusters() {
        Map<Integer, Collection<Integer>> input = new HashMap();
        input.put(1, Arrays.asList(1,2));
        input.put(2, Arrays.asList(2,1));
        input.put(3, Arrays.asList(3,4,5,6));
        input.put(4, Arrays.asList(3,4,5,6));
        input.put(5, Arrays.asList(3,4,5,6));
        input.put(6, Arrays.asList(3,4,5,6));
        input.put(7, Arrays.asList(7,8));
        input.put(8, Arrays.asList(8,7));

        assertThat(getClusterCounterBuilder().withInput(input).build().countClusters()).isEqualTo(3);
    }
}
