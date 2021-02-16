package com.acrylic;

import com.acrylic.main.MainToolBar;
import com.acrylic.sections.AbstractSection;
import com.acrylic.windowexpander.StageWindowExpander;
import com.acrylic.windowexpander.WindowExpander;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public interface Program {

    AbstractSection getDefaultSection();

    @NotNull
    AbstractSection getCurrentSection();

    void switchScene(@NotNull AbstractSection section);

    default void switchToDefaultScene() {
        switchScene(getDefaultSection());
    }

    default void switchToBack() {
        AbstractSection section = getDefaultSection().getParentSection();
        if (section != null)
            switchScene(section);
    }

    @NotNull
    Stage getPrimaryStage();

    @NotNull
    MainToolBar getToolBar();

    default void decorate(@NotNull Parent parent) {
        if (parent instanceof Pane) {
            MainToolBar mainToolBar = getToolBar();
            System.out.println("TTTTTT");
            ((Pane) parent).getChildren().add(mainToolBar);
        }
    }

}
