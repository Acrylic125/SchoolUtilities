package com.acrylic.main;

import com.acrylic.utils.CSSBuilder;
import com.acrylic.utils.FXUtils;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

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
        setPrefSize(100, 200);
        FXUtils.setMinMaxSizeByFactorFromPref(this, 0.5f, 2f);
        setStyle(CSSBuilder.builder()
                .addBackgroundColor(Color.LAVENDER)
                .addTextFill(Color.AZURE)
                .addFontWeight(FontWeight.BOLD)
                .build()
        );
    }

}
