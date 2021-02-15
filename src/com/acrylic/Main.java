package com.acrylic;

import com.acrylic.main.MainToolBar;
import com.acrylic.sections.AbstractSection;
import com.acrylic.windowexpander.StageWindowExpander;
import com.acrylic.windowexpander.WindowExpander;
import com.acrylic.utils.StageBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main
        extends Application
        implements Program {

    private MainToolBar mainToolBar;
    private static Program program;
    public static double DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 430;
    private Stage primaryStage;
    private AbstractSection currentSection;

    @Override
    public void start(Stage primaryStage) throws Exception {
        program = this;
        this.currentSection = new MainSection(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.primaryStage = new StageBuilder(primaryStage)
                .setScene(this.currentSection.getScene())
                .setF11FullScreen(true)
                .setStageStyle(StageStyle.TRANSPARENT)
                .setTitle("School Utilities")
                .getStage();
        primaryStage.show();
        mainToolBar = new MainToolBar(0, 0);
        addToolBar();
    }

    private void addToolBar() {
        WindowExpander windowExpander = new StageWindowExpander(primaryStage, WindowExpander.Setting.THIS_AND_FIRST_CHILD);
        Parent root = primaryStage.getScene().getRoot();
        if (root instanceof Pane) {
            System.out.println("TTTTTT");
            ((Pane) root).getChildren().add(mainToolBar);
            windowExpander.relocateIfContactWithNodes(mainToolBar);
        }
    }

    @Override
    public AbstractSection getDefaultSection() {
        try {
            return new MainSection();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public @NotNull AbstractSection getCurrentSection() {
        return currentSection;
    }

    @Override
    public void switchScene(@NotNull AbstractSection section) {
        this.currentSection = section;
        Scene scene = currentSection.getScene();
        Parent root = scene.getRoot();
        if (root instanceof Pane)
            ((Pane) root).getChildren().remove(mainToolBar);
        primaryStage.setScene(scene);
        addToolBar();
        primaryStage.show();
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
