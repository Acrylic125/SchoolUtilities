package com.acrylic.utils;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

public class StageBuilder {

    public static final EventHandler<KeyEvent> F11_FULL_SCREEN = event -> {
        Object objStage = event.getSource();
        if (event.getCode().equals(KeyCode.F11) && objStage instanceof Stage) {
            Stage stage = (Stage) objStage;
            stage.setFullScreen(!stage.isFullScreen());
        }
    };

    private final Stage stage;

    public StageBuilder() {
        this(new Stage());
    }

    public StageBuilder(@NotNull Stage stage) {
        this.stage = stage;
    }

    public StageBuilder setTitle(@NotNull String title) {
        stage.setTitle(title);
        return this;
    }

    public StageBuilder setScene(@NotNull Scene scene) {
        stage.setScene(scene);
        return this;
    }

    public StageBuilder setFullScreen(boolean fullScreen) {
        stage.setFullScreen(fullScreen);
        return this;
    }

    public StageBuilder setF11FullScreen(boolean b) {
        if (b)
            stage.addEventHandler(KeyEvent.KEY_PRESSED, F11_FULL_SCREEN);
        else
            stage.removeEventHandler(KeyEvent.KEY_PRESSED, F11_FULL_SCREEN);
        return this;
    }

    public StageBuilder setStageStyle(@NotNull StageStyle stageStyle) {
        stage.initStyle(stageStyle);
        return this;
    }

    @NotNull
    public Stage getStage() {
        return stage;
    }
}
