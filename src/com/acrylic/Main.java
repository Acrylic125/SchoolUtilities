package com.acrylic;

import com.acrylic.utils.SceneBuilder;
import com.acrylic.utils.StageBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static double DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 430;
    public static Scene DEFAULT_SCENE;
    public static Stage PRIMARY_STAGE;

    @Override
    public void start(Stage primaryStage) throws Exception {
        DEFAULT_SCENE = new SceneBuilder(getClass(), "main/maincontroller.fxml")
                .setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT)
                .addStyleSheet(getClass(), "resources/Theme.css")
                .build();
        new StageBuilder(primaryStage)
                .setScene(DEFAULT_SCENE)
                .setIconified(true)
                .setF11FullScreen(true)
                .setTitle("School Utilities")
                .getStage()
                .show();
        PRIMARY_STAGE = primaryStage;
        //IGNORE
        //double x1 = 0, y1 = 0;
        //double x2 = 0, y2 = -6;
        //double dot = (x1 * x2) + (y1 * y2);
        //System.out.println(Math.toDegrees(Math.acos(dot / (Math.sqrt((x1 * x1) + (y1 * y1)) * Math.sqrt((x2 * x2) + (y2 * y2))))));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
