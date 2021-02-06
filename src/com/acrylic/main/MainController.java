package com.acrylic.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML private Button settings_button;
    @FXML private Button about_button;
    @FXML private ScrollPane main;

    @FXML
    private void initialize() {
        VBox vb = new VBox();
        vb.setMinSize(main.getMinWidth(), main.getMinHeight());
        vb.setMaxSize(main.getMaxWidth(), main.getMaxHeight());
        vb.setPrefSize(main.getPrefWidth(), main.getPrefHeight());
        for (int i = 0; i < 100; i++) {
            vb.getChildren().add(new MainMenuOptionButton("Hello"));
        }
        main.setContent(vb);
    }


}
