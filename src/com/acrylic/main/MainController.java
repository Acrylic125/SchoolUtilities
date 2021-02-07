package com.acrylic.main;

import com.acrylic.searcher.Searcher;
import com.acrylic.utils.FXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class MainController implements Searcher<MenuRedirectOption> {

    @FXML private ImageView homeImage;
    @FXML private ImageView searchImage;
    @FXML private AnchorPane mainAnchorPane;
    @FXML private TextArea search;
    @FXML private Button settings_button;
    @FXML private Button about_button;
    @FXML private ScrollPane main;

    private final GridPane selectionGrid = new GridPane();
    private final Collection<MenuRedirectOption> menuRedirectOptions = new ArrayList<>();

    @FXML
    private void initialize() {
        main.setPadding(new Insets(30));
        FXUtils.cloneSizeFrom(selectionGrid, main);
        selectionGrid.setHgap(20);
        selectionGrid.setVgap(20);
        addOptionWithGrid(new CiteOption(), 0, 0);
        addOptionWithGrid(new QuickLinksOption(), 0, 1);
        addOptionWithGrid(new GPACalculatorOption(), 1, 0);
        main.setContent(selectionGrid);
        setScrollingSpeed(1.3f);
    }

    public void addOptionWithGrid(@NotNull MenuRedirectOption option, int x, int y) {
        addOption(option);
        selectionGrid.add(option.getButton(), x, y);
    }

    public void addOption(@NotNull MenuRedirectOption option) {
        menuRedirectOptions.add(option);
    }

    private void setScrollingSpeed(float speed) {
        main.getContent().setOnScroll(event -> main.setVvalue(main.getVvalue() + -(event.getDeltaY() * speed)));
    }

    @Override
    public @NotNull Collection<MenuRedirectOption> getSearchFrom() {
        return menuRedirectOptions;
    }
}
