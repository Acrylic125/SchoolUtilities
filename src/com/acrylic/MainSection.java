package com.acrylic;

import com.acrylic.sections.AbstractSection;
import com.acrylic.utils.SceneBuilder;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MainSection extends AbstractSection {

    public MainSection() throws IOException {
        super(new SceneBuilder(Main.class, "main/maincontroller.fxml")
                .setSize(Main.DEFAULT_WIDTH, Main.DEFAULT_HEIGHT)
                .addStyleSheet(Main.class, "resources/Theme.css")
                .build());
    }
}
