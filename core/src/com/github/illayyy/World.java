package com.github.illayyy;

public class World {
    private final int width;
    private final int height;
    private final int[][] matrix;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
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
