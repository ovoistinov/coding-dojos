package com.ovoistinov.coding.dojos.dependency_tree;

import com.google.common.collect.Multimap;

import java.util.*;

public class BuildSequenceGenerator {
    private Multimap<String, String> map;
    private Stack<String> stack;

    public BuildSequenceGenerator(Multimap<String, String> map) {
        this.map = map;
        this.stack = new Stack();
    }

    public String getBuildSequence() {
        Set visitedNodes = Collections.synchronizedSet(new HashSet());
        map.asMap().keySet().stream().sequential()
                .filter(i -> !visitedNodes.contains(i))
                .forEach(s -> {
                    traverse(s, visitedNodes);
                });

        return extractBuildSequence();
    }

    private String extractBuildSequence() {
        String result = "";

        while (!stack.empty()){
            result += stack.pop();
        }

        return result;
    }

    private void traverse(String nodeId, Set<String> visitedNodes) {
        if (visitedNodes.contains(nodeId)) {
            return;
        }

        visitedNodes.add(nodeId);

        System.err.println("put to stack: " + nodeId);
        stack.push(nodeId);

        for (String relatedNode : map.get(nodeId)) {
            traverse(relatedNode, visitedNodes);
        }
    }
}
