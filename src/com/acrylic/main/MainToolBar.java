package com.acrylic.main;

import com.acrylic.utils.FXUtils;
import com.acrylic.windowexpander.StageWindowExpander;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MainToolBar extends AnchorPane {

    private static final double BUTTON_SIZE_X = 30, BUTTON_SIZE_Y = 20;
    private double beforeClipSizeX = 0, beforeClipSizeY = 0;
    private double beforeClipLocationX = 0, beforeClipLocationY = 0;
    private Button closeButton, resizeButton;

    public MainToolBar(double baseWidth, double baseHeight) {
        init(baseWidth, baseHeight);
    }

    public MainToolBar(double baseWidth, double baseHeight, Node... nodes) {
        super(nodes);
        init(baseWidth, baseHeight);
    }

    public void init(double baseWidth, double baseHeight) {
        closeButton = getCloseButton();
        resizeButton = getClipButton();
        getChildren().addAll(closeButton, resizeButton);
        setPrefHeight(20);
        FXUtils.setMinMaxSizeAsPref(this);
        FXUtils.setAnchorBindings(this, 0d, 0d, -1d, 0d);
        getStyleClass().add("main-tool-bar");
        initBaseSize(baseWidth, baseHeight);
    }

    private void initBaseSize(double baseWidth, double baseHeight) {
        beforeClipSizeX = baseWidth;
        beforeClipSizeY = baseHeight;
    }

    private void transformButton(@NotNull Button button, int elementFromRight) {
        button.setPrefWidth(BUTTON_SIZE_X);
        button.setPrefHeight(BUTTON_SIZE_Y);
        FXUtils.setMinMaxSizeAsPref(button);
        FXUtils.setAnchorBindings(button, 0d, BUTTON_SIZE_X * elementFromRight, -1d, -1d);
    }

    private Button getCloseButton() {
        Button button = new Button("X");
        button.getStyleClass().add("close-button");
        transformButton(button, 0);
        button.setOnMouseClicked(event -> {
            Window window = ((Node) event.getSource()).getScene().getWindow();
            if (window instanceof Stage)
                ((Stage) window).close();
        });
        return button;
    }

    private Button getClipButton() {
        Button button = new Button("-");
        transformButton(button, 1);
        button.setOnMouseClicked(event -> {
            Rectangle dimension = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            Window window = ((Node) event.getSource()).getScene().getWindow();
            double windowWidth = window.getWidth(), windowHeight = window.getHeight(),
                    x = window.getX(), y = window.getY();
            if (dimension.getWidth() != windowWidth || dimension.getHeight() != windowHeight || x != 0 || y != 0) {
                beforeClipSizeX = windowWidth;
                beforeClipSizeY = windowHeight;
                beforeClipLocationX = x;
                beforeClipLocationY = y;
                if (window instanceof Stage)
                    StageWindowExpander.clipToMaxBounds((Stage) window);
            } else {
                window.setWidth(beforeClipSizeX);
                window.setHeight(beforeClipSizeY);
                window.setX(beforeClipLocationX);
                window.setY(beforeClipLocationY);
            }
        });
        return button;
    }

}
