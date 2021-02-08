package com.acrylic.utils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class FXUtils {

    public static String toHexColorWithID(@NotNull Color color) {
        return "#" + toHexColor(color);
    }

    public static String toHexColorWithID(int r, int g, int b, float a) {
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

    public static void cloneSizeFrom(@NotNull Region region, @NotNull Region cloneFrom) {
        cloneSizeFrom(region, cloneFrom, 1f, 1f);
    }

    public static void cloneSizeFrom(@NotNull Region region, @NotNull Region cloneFrom, double minFactor, double maxFactor) {
        region.setPrefSize(cloneFrom.getPrefWidth(), cloneFrom.getPrefHeight());
        setMinMaxSizeByFactorFromPref(region, minFactor, maxFactor);
    }

    public static void setMinMaxSizeByFactorFromPref(@NotNull Region region, double minFactor, double maxFactor) {
        double width = region.getPrefWidth(), height = region.getPrefHeight();
        region.setMinSize(width * minFactor, height * minFactor);
        region.setMaxSize(width * maxFactor, height * maxFactor);
    }

    public static void setMinMaxSizeAsPref(@NotNull Region region) {
        setMinMaxSizeByFactorFromPref(region, 1f, 1f);
    }

    public static void setInfiniteMaxSize(@NotNull Region region) {
        region.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    @Nullable
    public static Node getNode(@NotNull GridPane gridPane, int xIndex, int yIndex) {
        for (Node child : gridPane.getChildren())
            if (GridPane.getColumnIndex(child) == xIndex && GridPane.getRowIndex(child) == yIndex)
                return child;
        return null;
    }

}
