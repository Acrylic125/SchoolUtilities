package com.acrylic.windowexpander;

import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

public interface WindowExpanderActions {

    void expandTo(@NotNull MouseEvent event, double x1, double y1, double x2, double y2);

    void moveTo(@NotNull MouseEvent event, double x, double y);

}
