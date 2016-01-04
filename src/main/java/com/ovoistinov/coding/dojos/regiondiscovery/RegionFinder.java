package com.ovoistinov.coding.dojos.regiondiscovery;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static com.ovoistinov.coding.dojos.graphtraversal.ClusterCounter.getClusterCounterBuilder;

public class RegionFinder {
    private RectangularMap map;
    private HashMap<Position, Integer> labels;
    private Multimap<Integer, Integer> labelEquivalents;

    private RegionFinder(RectangularMap map) {
        this.map = map;
        labels = new HashMap<Position, Integer>();
        labelEquivalents = ArrayListMultimap.create();
    }

    public int countRegions() {
        int currentRegionLabel = 1;
        
        for (int y = 0; y < map.height(); y++) {
            for (int x = 0; x < map.width(); x++) {
                Position current = map.getPosition(x, y);
                
                //System.err.println("coords: " + current);
                if (!current.isEmpty()) {
                    // find 4-connectivity neighbours
                    Position left = x == 0 ? new Position(0, 0, 0) : map.getPosition(x - 1, y);
                    Position upper = y == 0 ? new Position(0, 0, 0) : map.getPosition(x, y - 1);
                    
                    if (left.isEmpty() && upper.isEmpty()) {
                        currentRegionLabel++;
                        
                        labels.put(current, currentRegionLabel);
                        labelEquivalents.put(currentRegionLabel, currentRegionLabel);
                        //registerLabelEquivalents(currentRegionLabel, currentRegionLabel);
                        //System.err.println("--1" + x + y);
                    } else {
                        //System.err.println("--2" + x + y);
                        int leftLabel = left == null ? currentRegionLabel : retrieveLabel(left);
                        int upperLabel = upper == null ? currentRegionLabel : retrieveLabel(upper);
                        int minimumLabel = leftLabel < upperLabel ? upperLabel : leftLabel;
                        
                        labels.put(current, minimumLabel);
                        //System.err.println("--2");
                        int internA = leftLabel > 0 ? leftLabel : currentRegionLabel;
                        int internB = upperLabel > 0 ? upperLabel : currentRegionLabel;
                        labelEquivalents.put(minimumFrom(internA, internB), maximumFrom(internA, internB));
                        //registerLabelEquivalents(leftLabel, upperLabel);
                    }
                }
            }
        }
        
        return countUniqueLabels();
    }

    private int minimumFrom(int a, int b) {
        return a < b ? a : b;
    }

    private int maximumFrom(int a, int b) {
        return a > b ? a : b;
    }
    
    private int retrieveLabel(Position position) {
        Integer result = labels.get(position);
        return result == null ? 0 : result;
    }
    
    private int countUniqueLabels() {
        return getClusterCounterBuilder().withInput(labelEquivalents.asMap()).build().countClusters();
    }

    public static RegionFinderBuilder regionFinderBuilder() {
        return new RegionFinderBuilder();
    }

    public static class RegionFinderBuilder {
        private RectangularMap map;

        public RegionFinderBuilder withMap(RectangularMap map) {
            this.map = map;
            return this;
        }

        public RegionFinder build() {
            return new RegionFinder(map);
        }
    }
}
