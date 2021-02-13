package com.acrylic.sections;

import com.acrylic.Main;
import com.acrylic.utils.SceneBuilder;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSection {

    private final Scene scene;

    public AbstractSection(@NotNull SceneBuilder sceneBuilder, double width, double height) {
        this.scene = sceneBuilder.setSize(width, height).build();
    }

    public AbstractSection(@NotNull SceneBuilder sceneBuilder, @NotNull Scene adoptSceneSize) {
        this(sceneBuilder, adoptSceneSize.getWidth(), adoptSceneSize.getHeight());
    }

    public AbstractSection(@NotNull SceneBuilder sceneBuilder) {
        this(sceneBuilder, Main.getProgram().getCurrentSection().getScene());
    }

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
