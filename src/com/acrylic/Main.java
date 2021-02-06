package com.acrylic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main/maincontroller.fxml"));
        primaryStage.setTitle("School Utilities");
        var scene = new Scene(root, 700, 430);
        scene.getStylesheets().add(getClass().getResource("resources/DarkTheme.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
