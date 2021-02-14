package com.acrylic.utils;

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

    public static boolean isPointInRegion(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2) {
        return isNumberInBetween(x, regionX1, regionX2) && isNumberInBetween(y, regionY1, regionY2);
    }

    public static boolean isPointInRegionBorder(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2) {
        return isPointInRegionBorder(x, y, regionX1, regionY1, regionX2, regionY2, 0);
    }

    public static boolean isPointInRegionBorder(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2, double errorBound) {
        return false; //isNumberInBetween(x, )
    }

    private static boolean isNumberInBetween(double toCheck, double a, double b) {
        return isNumberInBetween(toCheck, a, b, 0);
    }

    private static boolean isNumberInBetween(double toCheck, double a, double b, double errorBound) {
        return ((Math.abs(a - toCheck) + Math.abs(b - toCheck)) <= Math.abs(a - b) + errorBound);
    }

}
