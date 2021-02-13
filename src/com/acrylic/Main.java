package com.acrylic;

import com.acrylic.utils.SceneBuilder;
import com.acrylic.utils.StageBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application implements Program {

    private static Program program;
    public static double DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 430;
    private Scene defaultScene;
    private Stage primaryStage;
    private Scene currentScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        program = this;
        defaultScene = new SceneBuilder(getClass(), "main/maincontroller.fxml")
                .setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT)
                .addStyleSheet(getClass(), "resources/Theme.css")
                .build();
        primaryStage = new StageBuilder(primaryStage)
                .setIconified(true)
                .setF11FullScreen(true)
                .setTitle("School Utilities")
                .getStage();
        primaryStage.show();
        this.primaryStage = primaryStage;
        switchScene(defaultScene);
        //IGNORE
        //double x1 = 0, y1 = 0;
        //double x2 = 0, y2 = -6;
        //double dot = (x1 * x2) + (y1 * y2);
        //System.out.println(Math.toDegrees(Math.acos(dot / (Math.sqrt((x1 * x1) + (y1 * y1)) * Math.sqrt((x2 * x2) + (y2 * y2))))));
    }

    @Override
    public @NotNull Scene getDefaultScene() {
        return defaultScene;
    }

    @Override
    public @NotNull Scene getCurrentScene() {
        return currentScene;
    }

    @Override
    public void switchScene(@NotNull Scene scene) {
        this.currentScene = scene;
        primaryStage.setScene(scene);
    }

    @Override
    public @NotNull Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @NotNull
    public static Program getProgram() {
        return program;
    }
}
