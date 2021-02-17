package com.acrylic;

import com.acrylic.cache.SectionMap;
import com.acrylic.main.MainToolBar;
import com.acrylic.sections.AbstractSection;
import com.acrylic.utils.StageBuilder;
import com.acrylic.windowexpander.StageWindowExpander;
import com.acrylic.windowexpander.WindowExpander;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main
        extends Application
        implements Program {

    private static Program program;
    public static double DEFAULT_WIDTH = 700, DEFAULT_HEIGHT = 430;

    private MainToolBar mainToolBar;
    private Stage primaryStage;
    private AbstractSection currentSection;
    private final SectionMap<AbstractSection> map = new SectionMap<>();
    private volatile boolean reinitializeSize = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        program = this;
        this.mainToolBar = new MainToolBar(0, 0);
        MainSection mainSection = new MainSection(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.currentSection = mainSection;
        this.primaryStage = new StageBuilder(primaryStage)
                .setScene(this.currentSection.getScene())
                .setF11FullScreen(true)
                .setStageStyle(StageStyle.TRANSPARENT)
                .setTitle("School Utilities")
                .getStage();
        primaryStage.setMinWidth(100);
        primaryStage.setMinHeight(100);
        primaryStage.setMaxWidth(StageWindowExpander.getWindowSize().getWidth());
        primaryStage.setMaxHeight(StageWindowExpander.getWindowSize().getHeight());
        primaryStage.show();
        decorate(this.currentSection.getScene().getRoot());
        applyExpander();
        map.cache(mainSection);
    }

    private void applyExpander() {
        WindowExpander windowExpander = new StageWindowExpander(primaryStage, WindowExpander.Setting.THIS_AND_FIRST_CHILD);
        windowExpander.relocateIfContactWithNodes(mainToolBar);
    }

    @NotNull
    @Override
    public SectionMap<AbstractSection> getSectionMap() {
        return map;
    }

    @Override
    public AbstractSection getDefaultSection() {
        AbstractSection section = map.getSection(MainSection.class);
        if (section != null)
            return section;
        try {
            MainSection mainSection = new MainSection();
            map.cache(section);
            return mainSection;
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public @NotNull AbstractSection getCurrentSection() {
        return currentSection;
    }

    private void reinitializeSize() {
        if (reinitializeSize)
            return;
        reinitializeSize = true;
        double width = primaryStage.getWidth();
        primaryStage.setWidth(width - 1);
        new Thread(() -> {
            try {
                Thread.sleep(10);
                reinitializeSize = false;
                primaryStage.setWidth(width);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    @Override
    public void switchScene(@NotNull AbstractSection section) {
        this.currentSection = section;
        Scene scene = currentSection.getScene();
        primaryStage.setScene(scene);
        decorate(scene.getRoot());
        applyExpander();
        primaryStage.show();
        reinitializeSize();
    }

    @Override
    public @NotNull Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public @NotNull MainToolBar getToolBar() {
        return mainToolBar;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @NotNull
    public static Program getProgram() {
        return program;
    }
}
