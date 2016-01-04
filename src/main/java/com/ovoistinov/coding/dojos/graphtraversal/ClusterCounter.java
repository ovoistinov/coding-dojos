package com.ovoistinov.coding.dojos.graphtraversal;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClusterCounter {
    private Multimap<Integer, Integer> map;

    private ClusterCounter(Multimap<Integer, Integer> map) {
        this.map = map;
    }

    public int countClusters() {
        Set visitedNodes = Collections.synchronizedSet(new HashSet());

        return map.asMap().keySet().stream().sequential()
                .filter(i -> !visitedNodes.contains(i))
                .mapToInt(i -> {
                    traverse(i, visitedNodes);
                    return 1;
                }).sum();
    }

    private void traverse(Integer nodeId, Set<Integer> visitedNodes) {
        if (visitedNodes.contains(nodeId)) {
            return;
        }

        visitedNodes.add(nodeId);

        for (Integer relatedNode : map.get(nodeId)) {
            traverse(relatedNode, visitedNodes);
        }
    }

    public static ClusterCounterBuilder getClusterCounterBuilder() {
        return new ClusterCounterBuilder();
    }

    public static class ClusterCounterBuilder {
        private Map<Integer, Collection<Integer>> map;

        public ClusterCounterBuilder withInput(Map<Integer, Collection<Integer>> map) {
            this.map = map;
            return this;
        }

        public ClusterCounter build() {
            Multimap<Integer, Integer> multimap = ArrayListMultimap.create();

            for(Map.Entry<Integer,Collection<Integer>> e : map.entrySet()) {
                for (Integer value : e.getValue()) {
                    multimap.put(e.getKey(), value);
                }
            }

            return new ClusterCounter(multimap);
        }
    }
}
