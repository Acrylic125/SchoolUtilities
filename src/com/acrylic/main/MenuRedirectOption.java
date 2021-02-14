package com.acrylic.main;

import com.acrylic.enums.Alignment;
import com.acrylic.fxobjects.RedirectOption;
import com.acrylic.searcher.FXSearchable;
import com.acrylic.searcher.Searchable;
import com.acrylic.utils.CSSBuilder;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import org.ahocorasick.trie.Trie;
import org.jetbrains.annotations.NotNull;

public abstract class MenuRedirectOption
        implements FXSearchable, RedirectOption {

    private final Trie ids;
    private final String[] idArray;
    private final Button button;

    public MenuRedirectOption(@NotNull String[] ids, @NotNull Button button) {
        this.ids = Searchable.convertToIDTrie(ids);
        this.idArray = Searchable.convertToIDArray(ids);
        this.button = button;
    }

    public MenuRedirectOption(@NotNull String[] ids) {
        this(ids, new Button());
    }

    @NotNull
    @Override
    public Trie getIDs() {
        return ids;
    }

    @Override
    public String[] getIDArray() {
        return idArray;
    }

    @NotNull
    public Button getButton() {
        return button;
    }

    @Override
    public @NotNull Node getNode() {
        return button;
    }

    public void init(int r, int g, int b, float alpha) {
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
        initAnimation();
        button.setOnMouseClicked(this::handleMouseClicked);
    }

    @Override
    public @NotNull Parent getRoot() {
        return button;
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
        fadeOut.playFromStart();
    }

    private TextFlow getTextFlow() {
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(getTexts());
        return textFlow;
    }

    @NotNull
    public abstract Text[] getTexts();


}
