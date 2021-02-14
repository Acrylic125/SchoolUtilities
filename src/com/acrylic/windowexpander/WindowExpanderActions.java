package com.acrylic.windowexpander;

import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

public interface WindowExpanderActions {

    void expandTo(@NotNull MouseEvent event, double x, double y);

    void moveTo(@NotNull MouseEvent event, double x, double y);

}
