package com.acrylic.main;

import com.acrylic.utils.Alignment;
import com.acrylic.utils.CSSBuilder;
import com.acrylic.utils.FXUtils;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public abstract class MenuRedirectOption {

    private final String id;
    private final Button button;

    public MenuRedirectOption(@NotNull String id, @NotNull Button button) {
        this.id = id.toLowerCase(Locale.ROOT);
        this.button = button;
    }

    public MenuRedirectOption(@NotNull String id) {
        this(id, new Button());
    }

    @NotNull
    public String getID() {
        return id;
    }

    @NotNull
    public Button getButton() {
        return button;
    }

    public void init(int width, int height, int r, int g, int b, float alpha) {
        button.setPrefSize(width, height);
        FXUtils.setMinMaxSizeByFactorFromPref(button, 1f, 2f);
        button.setTextFill(Color.WHITE);
        button.setGraphic(getTextFlow());
        button.setStyle(CSSBuilder.builder()
                .addBackgroundColor(Color.rgb(r, g, b, alpha))
                .addFontWeight(FontWeight.BOLD)
                .addBackgroundRadius(25)
                .addAlignment(Alignment.TOP_LEFT)
                .addPadding(10)
                .build()
        );
    }

    private TextFlow getTextFlow() {
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(getTexts());
        return textFlow;
    }

    @NotNull
    public abstract Text[] getTexts();

    public static Text[] getDefaultFormatTexts(@NotNull String title, @NotNull String... texts) {
        Text titleText = new Text(title + "\n");
        titleText.setFill(Color.WHITE);
        titleText.setFont(Font.font(null, FontWeight.BOLD, 15));
        int l = texts.length;
        Text[] textsObj = new Text[l + 1];
        textsObj[0] = titleText;
        for (int i = 1; i <= l; i++) {
            Text strText = new Text((i == l) ? texts[i - 1] : texts[i - 1] + "\n");
            strText.setFill(Color.WHITE);
            strText.setFont(Font.font(null, FontWeight.THIN, 10));

            textsObj[i] = strText;
        }
        return textsObj;
    }

}
