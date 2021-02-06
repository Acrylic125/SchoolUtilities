package com.acrylic.utils;

import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public final class Utils {

    public static String toHexColorWithID(@NotNull Color color) {
        return "#" + toHexColor(color);
    }

    public static String toHexColorWithID(int r, int g, int b, int a) {
        return "#" + toHexColor(r, g, b, a);
    }

    public static String toHexColor(@NotNull Color color) {
        return toHex(color.getRed()) + toHex(color.getGreen()) + toHex(color.getBlue());
    }

    public static String toHexColor(int r, int g, int b, int a) {
        return toHex(r) + toHex(g) + toHex(b) + toHex(a);
    }

    public static String toHex(double v) {
        return toHex((int) v);
    }

    public static String toHex(int v) {
        String hex = Integer.toHexString(v);
        return (hex.length() == 1) ? "0" + hex : hex;
    }


}
