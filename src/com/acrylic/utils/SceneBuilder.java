package com.acrylic.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SceneBuilder {

    private double width = 300, height = 185;
    private Parent parent;
    private Paint paint;
    private Camera camera;
    private Cursor cursor;
    private List<String> styles = new ArrayList<>();

    public SceneBuilder() { }

    public SceneBuilder(@NotNull Parent parent) {
        this.parent = parent;
    }

    public SceneBuilder(@NotNull URL controllerPath) throws IOException {
        this.parent = FXMLLoader.load(controllerPath);
    }

    public SceneBuilder(@NotNull Class<?> clazz, @NotNull String controllerPath) throws IOException {
        this(clazz.getResource(controllerPath));
    }

    public double getWidth() {
        return width;
    }

    public SceneBuilder setSize(double width, double height) {
        return setWidth(width).setHeight(height);
    }

    public SceneBuilder setWidth(double width) {
        this.width = width;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public SceneBuilder setHeight(double height) {
        this.height = height;
        return this;
    }

    public Parent getParent() {
        return parent;
    }

    public SceneBuilder setParent(@NotNull Parent parent) {
        this.parent = parent;
        return this;
    }

    public Paint getPaint() {
        return paint;
    }

    public SceneBuilder setPaint(Paint paint) {
        this.paint = paint;
        return this;
    }

    public Camera getCamera() {
        return camera;
    }

    public SceneBuilder setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public SceneBuilder setCursor(Cursor cursor) {
        this.cursor = cursor;
        return this;
    }

    public SceneBuilder addStyleSheet(@NotNull Class<?> clazz, @NotNull String styleSheetPath) {
        return addStyleSheet(clazz.getResource(styleSheetPath).toString());
    }

    public SceneBuilder addStyleSheet(@NotNull String styleSheetPath) {
        styles.add(styleSheetPath);
        return this;
    }

    public SceneBuilder addStyleSheets(String... styleSheetPaths) {
        for (String styleSheetPath : styleSheetPaths)
            addStyleSheet(styleSheetPath);
        return this;
    }

    public SceneBuilder addStyleSheets(@NotNull Collection<? extends String> styleSheetPaths) {
        styles.addAll(styleSheetPaths);
        return this;
    }

    public SceneBuilder setStyleSheets(@NotNull List<String> styleSheetPaths) {
        styles = styleSheetPaths;
        return this;
    }

    public Scene build() {
        Scene scene = new Scene(parent, width, height);
        scene.setCursor(cursor);
        scene.setCamera(camera);
        scene.getStylesheets().addAll(styles);
        if (paint != null)
            scene.setFill(paint);
        return scene;
    }

}
