package com.acrylic.base;

import com.acrylic.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class BaseController {

    @FXML
    private ScrollPane main;
    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        backButton.setOnMouseClicked(mouseEvent -> Main.getProgram().switchToDefaultScene());
    }

    public ScrollPane getMain() {
        return main;
    }

    public Button getBackButton() {
        return backButton;
    }

}
