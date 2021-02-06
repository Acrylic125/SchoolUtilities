package com.acrylic.main;

import com.acrylic.utils.CSSBuilder;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MainMenuOptionButton extends Button {

    public MainMenuOptionButton() {
        super();
        init();
    }

    public MainMenuOptionButton(String s) {
        super(s);
        init();
    }

    public MainMenuOptionButton(String s, Node node) {
        super(s, node);
        init();
    }

    private void init() {
        setStyle(CSSBuilder.builder().addBackgroundColor(Color.BLACK).build());
    }

}
