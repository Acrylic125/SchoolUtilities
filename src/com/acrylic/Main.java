package com.acrylic;

import com.acrylic.utils.FXUtils;
import com.acrylic.utils.SceneBuilder;
import com.acrylic.utils.StageBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static double DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 430;

    @Override
    public void start(Stage primaryStage) throws Exception {
        new StageBuilder(primaryStage)
                .setScene(
                    new SceneBuilder(getClass(), "main/maincontroller.fxml")
                    .setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT)
                    .addStyleSheet(getClass(), "resources/Theme.css")
                    .build()
                )
                .setF11FullScreen(true)
                .setTitle("School Utilities")
                .getStage()
                .show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
