package com.ovoistinov.coding.dojos.regiondiscovery;

public class RectangularMap {
    private int[][] mapData;

    private RectangularMap(int[][] rectangularMap) {
        this.mapData = rectangularMap;
    }

    public Position getPosition(int x, int y) {
        return new Position(x, y, mapData[x][y]);
    }
    
    public int width() {
        return mapData.length;
    }

    public int height() {
        return mapData[0].length;
    }

    public static RectangularMapBuilder rectangularMapBuilder() {
        return new RectangularMapBuilder();
    }

    public static class RectangularMapBuilder {
        private static final String SEPARATOR_REGEXP = "\\|";
        private String input;

        public RectangularMapBuilder withInput(String input) {
            this.input = input;
            return this;
        }

        public RectangularMap build() {
            String[] rows = this.input.split(SEPARATOR_REGEXP);
            int width = rows[0].length();
            int height = rows.length;

            int[][] result = new int[width][height];

            for (int y=0; y < height; y++) {
                char[] currentRowChars = rows[y].toCharArray();

                for (int x=0; x < width; x++) {
                    result[x][y] = Character.getNumericValue(currentRowChars[x]);
                }
            }

            return new RectangularMap(result);
        }
    }
}
