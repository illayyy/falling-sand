package com.github.illayyy;

public class World {
    private final int width;
    private final int height;
    private int[][] matrix;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public void step() {
        int[][] newMatrix = new int[height][width];

        for (int y = 0; y < height; y++) {
            System.arraycopy(matrix[y], 0, newMatrix[y], 0, width);
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y < height - 1 && matrix[y][x] == 1 && matrix[y + 1][x] == 0) {
                    newMatrix[y][x] = 0;
                    newMatrix[y + 1][x] = 1;
                }
            }
        }

        matrix = newMatrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setCell(int x, int y, int value) {
        if (x < width && y < height && x >= 0 && y >= 0) {
            matrix[y][x] = value;
        }
    }
}
