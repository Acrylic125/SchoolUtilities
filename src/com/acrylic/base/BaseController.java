package com.acrylic.base;

import com.acrylic.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class BaseController {

    @FXML
    private AnchorPane global;
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
