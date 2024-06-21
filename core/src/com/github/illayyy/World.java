package com.github.illayyy;

public class World {
    private final int[][] matrix;

    public World(int width, int height) {
        matrix = new int[height][width];
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[0][1] = 1;

        matrix[height - 1][0] = 1;
        matrix[height - 2][0] = 1;
        matrix[height - 1][1] = 1;

        matrix[height - 1][width - 1] = 1;
        matrix[height - 2][width - 1] = 1;
        matrix[height - 1][width - 2] = 1;

        matrix[0][width - 1] = 1;
        matrix[1][width - 1] = 1;
        matrix[0][width - 2] = 1;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
