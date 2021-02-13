package com.acrylic.cite;

import com.acrylic.Main;
import com.acrylic.sections.AbstractSection;
import com.acrylic.utils.SceneBuilder;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class CitePage extends AbstractSection {

    public CitePage(@NotNull Scene currentScene) throws IOException {
        super(new SceneBuilder(Main.class, "base/basecontroller.fxml")
                .setSize(currentScene.getWidth(), currentScene.getHeight())
                .build()
        );
    }
}
