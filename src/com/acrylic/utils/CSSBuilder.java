package com.acrylic.utils;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.jetbrains.annotations.NotNull;

public final class CSSBuilder {

    public static CSSBuilder builder() {
        return new CSSBuilder();
    }

    private final StringBuilder stringBuilder;

    private CSSBuilder() {
        stringBuilder = new StringBuilder();
    }

    public CSSBuilder addPadding(int padding) {
        return add("-fx-padding", padding);
    }

    public CSSBuilder addPadding(int top, int right, int bottom, int left) {
        return add("-fx-padding", top + "px " + right + "px " + bottom + "px " + left + "px");
    }

    public CSSBuilder addMargin(int margin) {
        return add("-fx-margin", margin);
    }

    public CSSBuilder addMargin(int top, int right, int bottom, int left) {
        return add("-fx-margin", top + "px " + right + "px " + bottom + "px " + left + "px");
    }

    public CSSBuilder addBackground(@NotNull Color color) {
        return addBackground(Utils.toHexColorWithID(color));
    }

    public CSSBuilder addBackground(int r, int g, int b, int a) {
        return addBackground(Utils.toHexColorWithID(r, g, b, a));
    }

    public CSSBuilder addBackground(@NotNull String color) {
        return add("-fx-background", color);
    }

    public CSSBuilder addBackgroundColor(@NotNull Color color) {
        return addBackgroundColor(Utils.toHexColorWithID(color));
    }

    public CSSBuilder addBackgroundColor(int r, int g, int b, int a) {
        return addBackgroundColor(Utils.toHexColorWithID(r, g, b, a));
    }

    public CSSBuilder addBackgroundColor(@NotNull String color) {
        return add("-fx-background-color", color);
    }

    public CSSBuilder addTextFill(@NotNull Color color) {
        return addTextFill(Utils.toHexColorWithID(color));
    }

    public CSSBuilder addTextFill(int r, int g, int b, int a) {
        return addTextFill(Utils.toHexColorWithID(r, g, b, a));
    }

    public CSSBuilder addTextFill(@NotNull String color) {
        return add("-fx-text-fill", color);
    }

    public CSSBuilder add(@NotNull String key, @NotNull Object value) {
        return addString(key).addString(":").addString(value.toString());
    }

    public CSSBuilder addString(@NotNull String str) {
        stringBuilder.append(str);
        return this;
    }

    public String build() {
        return stringBuilder.toString();
    }
}
