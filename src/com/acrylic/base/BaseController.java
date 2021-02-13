package com.acrylic.base;

import com.acrylic.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BaseController {

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        backButton.setOnMouseClicked(mouseEvent -> Main.getProgram().switchToDefaultScene());
    }

}
