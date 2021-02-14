package com.acrylic.main;

import com.acrylic.utils.FXUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class MainToolBar extends ToolBar {

    private static final double BUTTON_SIZE_X = 30, BUTTON_SIZE_Y = 20;

    private final Button closeButton;
    private final Button clipToBoundButton;

    public MainToolBar() {
        this.closeButton = getCloseButton();
        this.clipToBoundButton = getClipButton();
        init();
    }

    public MainToolBar(Node... nodes) {
        super(nodes);
        this.closeButton = getCloseButton();
        this.clipToBoundButton = getClipButton();
        init();
    }

    public void init() {
        getItems().addAll(closeButton, clipToBoundButton);
        setMinHeight(20);
        FXUtils.setMinMaxSizeAsPref(this);
        FXUtils.setAnchorBindings(this, 0d, 0d, -1d, 0d);
    }

    private static void transformButton(@NotNull Button button, int elementFromRight) {
        button.setPrefWidth(BUTTON_SIZE_X);
        button.setPrefHeight(BUTTON_SIZE_Y);
        FXUtils.setMinMaxSizeAsPref(button);
        FXUtils.setAnchorBindings(button, 0d, BUTTON_SIZE_X * elementFromRight, -1d, -1d);
    }

    private static Button getCloseButton() {
        Button button = new Button("X");
        transformButton(button, 0);
        button.getStyleClass().add("close-button");
        return button;
    }

    private static Button getClipButton() {
        Button button = new Button("-");
        transformButton(button, 1);
        return button;
    }

}
