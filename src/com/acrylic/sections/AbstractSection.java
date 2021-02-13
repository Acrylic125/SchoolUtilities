package com.acrylic.sections;

import com.acrylic.Main;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSection {

    private final Scene scene;

    public AbstractSection(@NotNull Scene scene) {
        this.scene = scene;
        Main.getProgram().switchScene(scene);
    }

    @NotNull
    public Scene getScene() {
        return scene;
    }
}
