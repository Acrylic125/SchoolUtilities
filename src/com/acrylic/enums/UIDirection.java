package com.acrylic.enums;

public enum UIDirection {

    NORTH(0f, -1f),
    EAST(1f, 0),
    SOUTH(0f, 1f),
    WEST(-1f, 0),
    NORTH_EAST(NORTH, EAST),
    SOUTH_EAST(SOUTH, EAST),
    NORTH_WEST(NORTH, WEST),
    SOUTH_WEST(SOUTH, WEST);

    private final float x, y;

    UIDirection(float x, float y) {
        this.x = x;
        this.y = y;
        System.out.println(x + " " + y);
    }

    UIDirection(UIDirection d1, UIDirection d2) {
        this(nonZero(d1.getX(), d2.getX()), nonZero(d1.getY(), d2.getY()));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private static float nonZero(float a, float b) {
        return (a == 0) ? b : a;
    }

}
