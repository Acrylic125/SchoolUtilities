package com.acrylic.main;

import com.acrylic.utils.FXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
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
        vb.add(new CiteOption().getButton(), 0, 0);
        vb.add(new QuickLinksOption().getButton(), 0, 1);
        vb.add(new GPACalculatorOption().getButton(), 1, 0);
        main.setContent(vb);
        main.getContent().setOnScroll(event -> {
            double deltaY = event.getDeltaY() * 1.3;
            double vvalue = main.getVvalue();
            main.setVvalue(vvalue + -deltaY); // deltaY/width to make the scrolling equally fast regardless of the actual width of the component
        });
    }


}
