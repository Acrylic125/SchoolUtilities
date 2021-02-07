package com.acrylic.main;

import com.acrylic.searcher.FXSearchable;
import com.acrylic.utils.Alignment;
import com.acrylic.utils.CSSBuilder;
import com.acrylic.utils.FXUtils;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public abstract class MenuRedirectOption implements FXSearchable {

    private final String[] ids;
    private final Button button;
    private Color originalColor;

    public MenuRedirectOption(@NotNull String[] ids, @NotNull Button button) {
        this.ids = new String[ids.length];
        for (int i = 0; i < ids.length; i++)
            this.ids[i] = ids[i].toUpperCase(Locale.ROOT);
        this.button = button;
    }

    public MenuRedirectOption(@NotNull String[] ids) {
        this(ids, new Button());
    }

    public void disappear() {
        ScaleTransition start = new ScaleTransition(Duration.millis(1000));
        start.setNode(button);
        start.setToX(0);
        start.setToY(0);
        start.playFromStart();
    }

    public void appear() {
        ScaleTransition start = new ScaleTransition(Duration.millis(1000));
        start.setNode(button);
        start.setFromX(0);
        start.setFromY(0);
        start.setToX(1);
        start.setToY(1);
        start.playFromStart();
    }

    @NotNull
    @Override
    public String[] getIDs() {
        return ids;
    }

    @NotNull
    public Button getButton() {
        return button;
    }

    @Override
    public @NotNull Node getNode() {
        return button;
    }

    public void init(int width, int height, int r, int g, int b, float alpha) {
        button.setPrefSize(width, height);
        FXUtils.setMinMaxSizeByFactorFromPref(button, 1f, 2f);
        button.setTextFill(Color.WHITE);
        button.setGraphic(getTextFlow());
        originalColor = Color.rgb(r, g, b, alpha);
        button.setStyle(CSSBuilder.builder()
                .addBackgroundColor(originalColor)
                .addFontWeight(FontWeight.BOLD)
                .addBackgroundRadius(25)
                .addAlignment(Alignment.TOP_LEFT)
                .addPadding(10)
                .build()
        );
        initAnimation();
    }

    private void initAnimation() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300));
        fadeIn.setNode(button);
        fadeIn.setFromValue(0.7f);
        fadeIn.setToValue(1f);
        button.setOnMouseEntered(e -> fadeIn.playFromStart());
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300));
        fadeOut.setNode(button);
        fadeOut.setFromValue(1f);
        fadeOut.setToValue(0.7f);
        button.setOnMouseExited(e -> fadeOut.playFromStart());
        appear();
        fadeOut.playFromStart();
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
