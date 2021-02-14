package com.acrylic.windowexpander;

import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class StageWindowExpander extends WindowExpander {

    private final Stage stage;

    public StageWindowExpander(@NotNull Stage stage, @NotNull WindowExpander.Setting setting) {
        this.stage = stage;
        Scene scene = stage.getScene();
        includeListener(scene, setting);
    }

    @NotNull
    public Scene getScene() {
        return stage.getScene();
    }

    protected Bounds getSceneBounds() {
        Node node = getScene().getRoot();
        return node.localToScreen(node.getBoundsInLocal());
    }

    @Override
    protected void setCursor(@NotNull Cursor cursor) {
        getScene().setCursor(cursor);
    }

    @Override
    public double getWidth() {
        return stage.getWidth();
    }

    @Override
    public double getHeight() {
        return stage.getHeight();
    }

    @Override
    protected void moveTo(MouseEvent event, double x, double y) {
        stage.setX(x);
        stage.setY(y);
    }

    @Override
    protected void expandTo(MouseEvent event, double x, double y) {
        x = expandDirection.getX() * (x - screenX);
        y = expandDirection.getY() * (y - screenY);
        //System.out.println(cursorOffsetX + " " + cursorOffsetY);
        stage.setWidth(x + initWidth);
        if (expandDirection.getX() < 0)
            stage.setX(event.getScreenX() - cursorOffsetX);
        stage.setHeight(y + initHeight);
        if (expandDirection.getY() < 0)
            stage.setY(event.getScreenY() - cursorOffsetY);
    }

}
