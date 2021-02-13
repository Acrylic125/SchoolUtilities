package com.acrylic;

import com.acrylic.sections.AbstractSection;
import com.acrylic.utils.SceneBuilder;

import java.io.IOException;

public class MainSection extends AbstractSection {

    public MainSection() throws IOException {
        super(new SceneBuilder(Main.class, "main/maincontroller.fxml")
                .addStyleSheet(Main.class, "resources/Theme.css")
        );
    }

    public MainSection(double width, double height) throws IOException {
        super(new SceneBuilder(Main.class, "main/maincontroller.fxml")
                .addStyleSheet(Main.class, "resources/Theme.css"),
                width, height);
    }

    @Override
    public void initialize() {

    }
}
