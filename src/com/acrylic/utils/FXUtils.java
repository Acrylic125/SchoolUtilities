package com.acrylic.utils;

import javafx.scene.control.Control;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public final class FXUtils {

    public static String toHexColorWithID(@NotNull Color color) {
        return "#" + toHexColor(color);
    }

    public static String toHexColorWithID(int r, int g, int b, int a) {
        return "#" + toHexColor(r, g, b, a);
    }

    public static String toHexColor(@NotNull Color color) {
        return toHexColor(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
    }

    public static String toHexColor(double r, double g, double b, double a) {
        return String.format("%02x%02x%02x%02x",
                (int) Math.round(r * 255),
                (int) Math.round(g * 255),
                (int) Math.round(b * 255),
                (int) Math.round(a * 255));
    }

    public static void setMinMaxSizeByFactorFromPref(@NotNull Region region, float minFactor, float maxFactor) {
        double width = region.getPrefWidth(), height = region.getPrefHeight();
        region.setMinSize(width * minFactor, height * minFactor);
        region.setMaxSize(width * maxFactor, height * maxFactor);
    }

    public static void setMinMaxSizeAsPref(@NotNull Region region) {
        setMinMaxSizeByFactorFromPref(region, 1f, 1f);
    }

}
