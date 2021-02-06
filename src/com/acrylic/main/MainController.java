package com.acrylic.main;

import com.acrylic.utils.FXUtils;
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
        FXUtils.cloneSizeFrom(vb, main);
        for (int i = 0; i < 1; i++) {
            vb.getChildren().add(new MainMenuOptionButton("Hello"));
        }
        main.setContent(vb);
    }


}
