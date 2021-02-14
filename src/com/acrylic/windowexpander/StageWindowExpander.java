package com.acrylic.windowexpander;

import com.acrylic.utils.MathUtils;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

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
        x = MathUtils.clamp(initWidth + (expandDirection.getX() * (x - screenX)), stage.getMinWidth(), stage.getMaxWidth());
        y = MathUtils.clamp(initHeight + (expandDirection.getY() * (y - screenY)), stage.getMinHeight(), stage.getMaxHeight());
        stage.setWidth(x);
        if (expandDirection.getX() < 0)
            stage.setX(event.getScreenX() - cursorOffsetX);
        stage.setHeight(y);
        if (expandDirection.getY() < 0)
            stage.setY(event.getScreenY() - cursorOffsetY);
    }

    @Override
    public void clipToMaxBounds() {
        stage.setX(0);
        stage.setY(0);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setWidth(dimension.getWidth());
        stage.setHeight(dimension.getHeight());
    }

}
