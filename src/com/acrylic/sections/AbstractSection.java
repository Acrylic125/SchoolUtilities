package com.acrylic.sections;

import com.acrylic.Main;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSection {

    private final Scene scene;

    public AbstractSection(@NotNull Scene scene) {
        this.scene = scene;
    }

    public void switchToScene() {
        Main.getProgram().switchScene(this);
    }

    @NotNull
    public Scene getScene() {
        return scene;
    }

    @Nullable
    public AbstractSection getParentSection() {
        return Main.getProgram().getDefaultSection();
    }

}
