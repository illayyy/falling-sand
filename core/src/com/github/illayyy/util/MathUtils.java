package com.github.illayyy.util;

public class MathUtils {
    public static boolean isInsideCircle(int x, int y, int cx, int cy, float radius) {
        float dx = cx - x;
        float dy = cy - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        return distance < radius;
    }
}
