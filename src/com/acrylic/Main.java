package com.acrylic;

import com.acrylic.sections.AbstractSection;
import com.acrylic.windowexpander.WindowExpander;
import com.acrylic.utils.StageBuilder;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main
        extends Application
        implements Program {

    double xOffset = 0, yOffset = 0;

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
        WindowExpander windowExpander = new WindowExpander(primaryStage, WindowExpander.Setting.THIS_AND_FIRST_CHILD);
        for (Node node : primaryStage.getScene().getRoot().getChildrenUnmodifiable()) {
            if (node.getClass().equals(ToolBar.class)) {
                System.out.println("T");
                windowExpander.relocateIfContactWithNodes(node);
            }
        }
        System.out.println(primaryStage.getScene().getRoot().getChildrenUnmodifiable().size());
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
        primaryStage.setScene(section.getScene());
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
