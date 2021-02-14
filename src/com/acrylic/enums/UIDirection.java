package com.acrylic.enums;

import javafx.scene.Cursor;

public enum UIDirection {

    NORTH(0f, -1f) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.N_RESIZE;
        }
    },
    EAST(1f, 0) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.E_RESIZE;
        }
    },
    SOUTH(0f, 1f) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.S_RESIZE;
        }
    },
    WEST(-1f, 0) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.W_RESIZE;
        }
    },
    NORTH_EAST(NORTH, EAST) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.NE_RESIZE;
        }
    },
    SOUTH_EAST(SOUTH, EAST) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.SE_RESIZE;
        }
    },
    NORTH_WEST(NORTH, WEST) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.NW_RESIZE;
        }
    },
    SOUTH_WEST(SOUTH, WEST) {
        @Override
        public Cursor getDirectionCursor() {
            return Cursor.SW_RESIZE;
        }
    };

    private final float x, y;

    UIDirection(float x, float y) {
        this.x = x;
        this.y = y;
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

    public abstract Cursor getDirectionCursor();

    private static float nonZero(float a, float b) {
        return (a == 0) ? b : a;
    }

}
