package com.acrylic;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;

public final class MainMenuScrollView {

    protected final ScrollPane pane;

    protected MainMenuScrollView(int offsetX) {
        pane = new ScrollPane();
        var colorpicker = new ColorPicker();
        colorpicker.setScaleX(10);
        colorpicker.setScaleY(10);
        colorpicker.setScaleZ(10);
        pane.setContent(colorpicker);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

}
