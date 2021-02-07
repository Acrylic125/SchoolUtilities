package com.acrylic.utils;

import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.NotNull;

public final class CSSBuilder {

    public static CSSBuilder builder() {
        return new CSSBuilder();
    }

    public enum Unit {
        PX("px"), EM("em");

        private final String unit;

        Unit(@NotNull String unit) {
            this.unit = unit;
        }

        public String getUnit() {
            return unit;
        }
    }

    private final StringBuilder stringBuilder;
    private String unit = "px";

    private CSSBuilder() {
        stringBuilder = new StringBuilder();
    }

    public CSSBuilder useUnit(@NotNull Unit unit) {
        return useUnit(unit.getUnit());
    }

    public CSSBuilder useUnit(@NotNull String unit) {
        this.unit = unit;
        return this;
    }

    public CSSBuilder addTextAlignment(@NotNull TextAlignment textAlignment) {
        return add("-fx-text-alignment", textAlignment.name());
    }

    public CSSBuilder addAlignment(@NotNull Alignment alignment) {
        return add("-fx-alignment", alignment.getName());
    }

    public CSSBuilder addOpacity(float opacity) {
        return add("-fx-opacity", opacity);
    }

    public CSSBuilder addBackgroundRadius(int radius) {
        return add("-fx-background-radius", radius + unit);
    }

    public CSSBuilder addBackgroundRadius(int rightTop, int leftTop, int bottomLeft, int bottomRight) {
        return add("-fx-background-radius", format4intsWithUnit(rightTop, leftTop, bottomLeft, bottomRight));
    }

    public CSSBuilder addPadding(int padding) {
        return add("-fx-padding", padding);
    }

    public CSSBuilder addPadding(int top, int right, int bottom, int left) {
        return add("-fx-padding", format4intsWithUnit(top, right, bottom, left));
    }

    public CSSBuilder addMargin(int margin) {
        return add("-fx-margin", margin);
    }

    public CSSBuilder addMargin(int top, int right, int bottom, int left) {
        return add("-fx-margin", format4intsWithUnit(top, right, bottom, left));
    }

    public CSSBuilder addBackground(@NotNull Color color) {
        return addBackground(FXUtils.toHexColorWithID(color));
    }

    public CSSBuilder addBackground(int r, int g, int b, float a) {
        return addBackground(FXUtils.toHexColorWithID(r, g, b, a));
    }

    public CSSBuilder addBackground(@NotNull String color) {
        return add("-fx-background", color);
    }

    public CSSBuilder addBackgroundColor(@NotNull Color color) {
        return addBackgroundColor(FXUtils.toHexColorWithID(color));
    }

    public CSSBuilder addBackgroundColor(int r, int g, int b, float a) {
        return addBackgroundColor(FXUtils.toHexColorWithID(r, g, b, a));
    }

    public CSSBuilder addBackgroundColor(@NotNull String color) {
        return add("-fx-background-color", color);
    }

    public CSSBuilder addTextFill(@NotNull Color color) {
        return addTextFill(FXUtils.toHexColorWithID(color));
    }

    public CSSBuilder addTextFill(int r, int g, int b, float a) {
        return addTextFill(FXUtils.toHexColorWithID(r, g, b, a));
    }

    public CSSBuilder addTextFill(@NotNull String color) {
        return add("-fx-text-fill", color);
    }

    public CSSBuilder addFontWeight(@NotNull FontWeight fontWeight) {
        return addFontWeight(fontWeight.getWeight());
    }

    public CSSBuilder addFontWeight(int weight) {
        return add("-fx-font-weight", weight);
    }

    public CSSBuilder addFontWeight(@NotNull String fontWeight) {
        return add("-fx-font-weight", fontWeight);
    }

    public CSSBuilder addHeight(int height) {
        return add("-fx-height", height + unit);
    }

    public CSSBuilder addWidth(int width) {
        return add("-fx-width", width + unit);
    }

    public CSSBuilder add(@NotNull String key, @NotNull Object value) {
        return addString(key).addString(":").addString(value.toString()).addString("; ");
    }

    public CSSBuilder addString(@NotNull String str) {
        stringBuilder.append(str);
        return this;
    }

    public String build() {
        return stringBuilder.toString();
    }

    private String format4intsWithUnit(int a, int b, int c, int d) {
        return a + unit + " " + b + unit + " " + c + unit + " " + d + unit;
    }

}
