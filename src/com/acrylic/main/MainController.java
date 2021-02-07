package com.acrylic.main;

import com.acrylic.utils.FXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML private ImageView homeImage;
    @FXML private ImageView searchImage;
    @FXML private AnchorPane mainAnchorPane;
    @FXML private TextArea search;
    @FXML private Button settings_button;
    @FXML private Button about_button;
    @FXML private ScrollPane main;

    @FXML
    private void initialize() {
        GridPane vb = new GridPane();
        main.setPadding(new Insets(30));
        FXUtils.cloneSizeFrom(vb, main);
        vb.setHgap(20);
        vb.setVgap(20);
        MainMenuOptionButton button = new MainMenuOptionButton("Hello");
        vb.add(button, 0, 0);
        MainMenuOptionButton newButton = new MainMenuOptionButton("Hello");
        vb.add(newButton, 1, 0);
        main.setContent(vb);
    }


}
