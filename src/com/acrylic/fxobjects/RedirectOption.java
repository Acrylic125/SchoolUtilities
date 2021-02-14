package com.acrylic.fxobjects;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public interface RedirectOption extends FXNode {

    void handleMouseClicked(@NotNull MouseEvent event);

    static Text[] getDefaultFormatTexts(@NotNull String title, @NotNull String... texts) {
        return getDefaultFormatTexts(Color.WHITE, title, texts);
    }

    static Text[] getDefaultFormatTexts(@NotNull Color textColor, @NotNull String title, @NotNull String... texts) {
        Text titleText = new Text(title + "\n");
        titleText.setFill(textColor);
        titleText.setFont(Font.font(null, FontWeight.BOLD, 15));
        int l = texts.length;
        Text[] textsObj = new Text[l + 1];
        textsObj[0] = titleText;
        for (int i = 1; i <= l; i++) {
            Text strText = new Text((i == l) ? texts[i - 1] : texts[i - 1] + "\n");
            strText.setFill(textColor);
            strText.setFont(Font.font(null, FontWeight.THIN, 10));
            textsObj[i] = strText;
        }
        return textsObj;
    }

}
