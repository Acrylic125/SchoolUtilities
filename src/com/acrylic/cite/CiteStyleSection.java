package com.acrylic.cite;

import com.acrylic.sections.AbstractSection;
import com.acrylic.utils.SceneBuilder;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

public abstract class CiteStyleSection extends AbstractSection {

    public CiteStyleSection(@NotNull SceneBuilder sceneBuilder, double width, double height) {
        super(sceneBuilder, width, height);
    }

    public CiteStyleSection(@NotNull SceneBuilder sceneBuilder, @NotNull Scene adoptSceneSize) {
        super(sceneBuilder, adoptSceneSize);
    }

    public CiteStyleSection(@NotNull SceneBuilder sceneBuilder) {
        super(sceneBuilder);
    }

    public CiteStyleSection(@NotNull Scene scene) {
        super(scene);
    }

    @Override
    public void initialize() {

    }
}
