package com.ovoistinov.coding.dojos.dependency_tree;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;

public class BuildSequenceGeneratorBuilder {
    private Map<String, Collection<String>> map;

    public static BuildSequenceGeneratorBuilder getBuildSequenceGenerator() {
        return new BuildSequenceGeneratorBuilder();
    }

    public BuildSequenceGeneratorBuilder withInput(Map<String, Collection<String>> map) {
        this.map = map;
        return this;
    }

    public BuildSequenceGenerator build() {
        Multimap<String, String> multimap = ArrayListMultimap.create();

        for(Map.Entry<String,Collection<String>> e : map.entrySet()) {
            for (String value : e.getValue()) {
                multimap.put(e.getKey(), value);
            }
        }

        return new BuildSequenceGenerator(multimap);
    }
}
