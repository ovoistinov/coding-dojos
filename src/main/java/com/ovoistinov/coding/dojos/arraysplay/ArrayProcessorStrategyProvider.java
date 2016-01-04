package com.ovoistinov.coding.dojos.arraysplay;


import com.ovoistinov.coding.dojos.arraysplay.strategies.SimpleArrayMissingNegativeOrPositiveFinder;
import com.ovoistinov.coding.dojos.arraysplay.strategies.SimpleArrayMissingNumberFinder;

import java.util.HashMap;
import java.util.Map;

import static com.ovoistinov.coding.dojos.arraysplay.ArrayProcessorStrategy.FIND_MISSING_PPOSITIVE_NUMBER_FIXED;
import static com.ovoistinov.coding.dojos.arraysplay.ArrayProcessorStrategy.FIND_MISSING_POSITIVE_OR_NEGATIVE_NUMBER;

public class ArrayProcessorStrategyProvider {
    private Map<ArrayProcessorStrategy, ArrayProcessor> processors;

    private ArrayProcessorStrategyProvider() {
        processors = new HashMap();
        processors.put(FIND_MISSING_PPOSITIVE_NUMBER_FIXED, new SimpleArrayMissingNumberFinder());
        processors.put(FIND_MISSING_POSITIVE_OR_NEGATIVE_NUMBER, new SimpleArrayMissingNegativeOrPositiveFinder());
    }

    public static ArrayProcessorStrategyProvider getArrayProcessorStrategy() {
        return new ArrayProcessorStrategyProvider();
    }

    public ArrayProcessor getArrayProcessor(ArrayProcessorStrategy processor) {
        return processors.get(processor);
    }


}
