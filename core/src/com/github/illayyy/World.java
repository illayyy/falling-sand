package com.github.illayyy;

import com.github.illayyy.material.Air;
import com.github.illayyy.material.Material;
import com.github.illayyy.material.Solid;

import java.util.Arrays;

public class World {
    private final int width;
    private final int height;
    private Material[][] matrix;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new Material[height][width];

        for (Material[] row : matrix) {
            Arrays.fill(row, new Air());
        }
    }

    private static <T> void swap(T[][] matrix, int y1, int x1, int y2, int x2) {
        T temp = matrix[y1][x1];
        matrix[y1][x1] = matrix[y2][x2];
        matrix[y2][x2] = temp;
    }

    public void step() {
        Material[][] newMatrix = new Material[height][width];

        for (int y = 0; y < height; y++) {
            System.arraycopy(matrix[y], 0, newMatrix[y], 0, width);
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y < height - 1) {
                    if (matrix[y][x] instanceof Solid) {
                        if (!(matrix[y + 1][x] instanceof Solid)) {
                            swap(newMatrix, y, x, y + 1, x);
                        }
                    }
                }
            }
        }

        matrix = newMatrix;
    }

    public Material[][] getMatrix() {
        return matrix;
    }

    public void setCell(int x, int y, Material material) {
        if (x < width && y < height && x >= 0 && y >= 0) {
            matrix[y][x] = material;
        }
    }
}
