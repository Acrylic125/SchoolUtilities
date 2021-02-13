package com.acrylic;

import com.acrylic.sections.AbstractSection;
import javafx.scene.Scene;
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

}
