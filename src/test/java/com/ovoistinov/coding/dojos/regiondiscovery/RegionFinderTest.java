package com.ovoistinov.coding.dojos.regiondiscovery;

import org.junit.Test;

import static com.ovoistinov.coding.dojos.regiondiscovery.RegionFinder.regionFinderBuilder;
import static org.assertj.core.api.Assertions.assertThat;

import static com.ovoistinov.coding.dojos.regiondiscovery.RectangularMap.rectangularMapBuilder;

public class RegionFinderTest {
    @Test
    public void findsNoIslandsWhenInputIsOs() {
        RectangularMap map = rectangularMapBuilder().withInput(
                    "00000|" + 
                    "00000")
                .build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(0);
    }

    @Test
    public void findsSingleIsland() {
        RectangularMap map = rectangularMapBuilder().withInput("00000|00000|00100|00000|00000").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(1);
    }

    @Test
    public void findsTwoIslands() {
        RectangularMap map = rectangularMapBuilder().withInput("00000|01000|00010").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(2);
    }

    @Test
    public void findsThreeIslandsWhenManyElementInOneRow() {
        RectangularMap map = rectangularMapBuilder().withInput("00110|00001|11100").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(3);
    }

    @Test
    public void findsTwoIslandsWhenManyIslandsInOneRow() {
        RectangularMap map = rectangularMapBuilder().withInput("10110").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(2);
    }

    @Test
    public void findsOneVerticalIslandFromTwoRows() {
        RectangularMap map = rectangularMapBuilder().withInput("00100|00100").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(1);
    }

    @Test
    public void findsTwoVerticalIslandsFromThreeRows() {
        RectangularMap map = rectangularMapBuilder().withInput("00100|00101|00001").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(2);
    }

    @Test
    public void findsOneVerticalIslandsFromThreeRows() {
        RectangularMap map = rectangularMapBuilder().withInput("01000|01000|01000").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(1);
    }

    @Test
    public void findsOneComplexIslandFromTwoRows() {
        RectangularMap map = rectangularMapBuilder().withInput("00100|01100").build();
        assertThat(regionFinderBuilder().withMap(map).build().countRegions()).isEqualTo(1);
    }
}
