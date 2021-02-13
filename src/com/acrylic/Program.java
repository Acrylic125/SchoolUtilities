package com.acrylic;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public interface Program {

    @NotNull
    Scene getDefaultScene();

    @NotNull
    Scene getCurrentScene();

    void switchScene(@NotNull Scene scene);

    @NotNull
    Stage getPrimaryStage();

}
