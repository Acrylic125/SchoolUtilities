package com.acrylic.utils;

import javafx.geometry.Bounds;
import org.jetbrains.annotations.NotNull;

public class MathUtils {

    public static boolean isBitInMask(int bitMask, int bit) {
        return (bitMask & bit) == bit;
    }

    public static int setBitToMask(int bitMask, int bit, boolean flag) {
        return (flag) ? setBitToMask(bitMask, bit) : removeBitFromMask(bitMask, bit);
    }

    public static int setBitToMask(int bitMask, int bit) {
        return bitMask | bit;
    }

    public static int removeBitFromMask(int bitMask, int bit) {
        return bitMask & ~bit;
    }

    /**
     * Point in Region.
     */
    public static boolean isPointInRegion(double x, double y, @NotNull Bounds bounds) {
        return isPointInRegion(x, y, bounds, 0);
    }

    public static boolean isPointInRegion(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2) {
        return isPointInRegion(x, y, regionX1, regionY1, regionX2, regionY2, 0);
    }

    public static boolean isPointInRegion(double x, double y, @NotNull Bounds bounds, double errorBound) {
        return isPointInRegion(x, y, bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY(), errorBound);
    }

    public static boolean isPointInRegion(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2, double errorBound) {
        return isNumberInBetween(x, regionX1, regionX2) && isNumberInBetween(y, regionY1, regionY2, errorBound);
    }

    /**
     * Point in line.
     */
    public static boolean isPointInRegionBorder(double x, double y, @NotNull Bounds bounds) {
        return isPointInRegionBorder(x, y, bounds, 0);
    }

    public static boolean isPointInRegionBorder(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2) {
        return isPointInRegionBorder(x, y, regionX1, regionY1, regionX2, regionY2, 0);
    }

    public static boolean isPointInRegionBorder(double x, double y, @NotNull Bounds bounds, double errorBound) {
        return isPointInRegionBorder(x, y, bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY(), errorBound);
    }

    /**
     * x1, y1 -> x2, y1
     * x1, y1 -> x1, y2
     * x2, y2 -> x1, y2
     * x2, y2 -> x2, y1
     */
    public static boolean isPointInRegionBorder(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2, double errorBound) {
        return isNumberInRange(x, regionX1, errorBound) ||
                isNumberInRange(x, regionX2, errorBound) ||
                isNumberInRange(y, regionY1, errorBound) ||
                isNumberInRange(y, regionY2, errorBound);
    }

    public static boolean isNumberInRange(double toCheck, double v, double range) {
        return isNumberInBetween(toCheck, v - range, v + range, 0);
    }

    public static boolean isNumberInBetween(double toCheck, double a, double b) {
        return isNumberInBetween(toCheck, a, b, 0);
    }

    public static boolean isNumberInBetween(double toCheck, double a, double b, double errorBound) {
        return ((Math.abs(a - toCheck) + Math.abs(b - toCheck)) <= Math.abs(a - b) + errorBound);
    }

    /**
     * Distance.
     */
    public static double squared(double v) {
        return v * v;
    }

    public static double distanceSquared(double x1, double y1, double x2, double y2) {
        return squared(x1 - x2) + squared(y1 - y2);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(distance(x1, y1, x2, y2));
    }

}
