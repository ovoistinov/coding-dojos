package com.ovoistinov.coding.dojos.arraysplay;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.ovoistinov.coding.dojos.arraysplay.ArrayProcessorStrategy.FIND_MISSING_PPOSITIVE_NUMBER_FIXED;
import static com.ovoistinov.coding.dojos.arraysplay.ArrayProcessorStrategy.FIND_MISSING_POSITIVE_OR_NEGATIVE_NUMBER;
import static com.ovoistinov.coding.dojos.arraysplay.ArrayProcessorStrategyProvider.getArrayProcessorStrategy;
import static org.assertj.core.api.Assertions.assertThat;

public class ArraysPlayTest {
    @Rule public ExpectedException exception = ExpectedException.none();

    @Test
    public void findsMissingNumberFromArray() {
        int[] input = new int[] {1, 2, 3, 4, 6, 7, 8, 9, 10};
        assertThat(getArrayProcessorStrategy().getArrayProcessor(FIND_MISSING_PPOSITIVE_NUMBER_FIXED).process(input)).isEqualTo(5);
    }

    @Test
    public void findMissingNumberFromNegativeAndPositiveArray() {
        int[] input = new int[] {7, 8, 4, 5};
        assertThat(getArrayProcessorStrategy().getArrayProcessor(FIND_MISSING_POSITIVE_OR_NEGATIVE_NUMBER).process(input)).isEqualTo(6);
    }

    @Test
    public void reportsExceptionWhenNoMissingNumbersFound() {
        int[] input = new int[] {7, 8, 9};

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There are no missing numbers");

        getArrayProcessorStrategy().getArrayProcessor(FIND_MISSING_POSITIVE_OR_NEGATIVE_NUMBER).process(input);
    }
}
