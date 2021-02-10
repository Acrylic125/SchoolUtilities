package com.acrylic.main;

import com.acrylic.paneaggregators.CollectionGridAggregator;
import com.acrylic.utils.FXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;

public class MainController {

    @FXML private ImageView homeImage;
    @FXML private ImageView searchImage;
    @FXML private AnchorPane mainAnchorPane;
    @FXML private TextField search;
    @FXML private Button settings_button;
    @FXML private Button about_button;
    @FXML private ScrollPane main;

    private final CollectionGridAggregator selectionGrid = new CollectionGridAggregator(new GridPane());
    private final MainSearcher searcher = new MainSearcher(this);

    @FXML
    private void initialize() {
        GridPane gridPane = selectionGrid.getPane();
        main.setPadding(new Insets(30));
        FXUtils.cloneSizeFrom(gridPane, main);
        FXUtils.setMinMaxSizeByFactorFromPref(gridPane, 1f, 2f);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        selectionGrid.setSizeX(200);
        selectionGrid.setSizeY(200);
        selectionGrid.setBaseRows(2);
        selectionGrid.setBaseColumns(2);
        addOptionWithGrid(new CiteOption(), 0, 0);
        addOptionWithGrid(new QuickLinksOption(), 0, 1);
        addOptionWithGrid(new GPACalculatorOption(), 1, 0);
        main.setContent(gridPane);
        main.setFitToHeight(true);
        main.setFitToWidth(true);
        setScrollingSpeed(1.3f);
        searcher.initSearchBar();
    }

    public void addOptionWithGrid(@NotNull MenuRedirectOption option, int x, int y) {
        searcher.addOption(option);
        selectionGrid.getPane().add(option.getButton(), x, y);
    }

    private void setScrollingSpeed(float speed) {
        main.getContent().setOnScroll(event -> main.setVvalue(main.getVvalue() + -(event.getDeltaY() * speed)));
    }

    public ImageView getHomeImage() {
        return homeImage;
    }

    public ImageView getSearchImage() {
        return searchImage;
    }

    public AnchorPane getMainAnchorPane() {
        return mainAnchorPane;
    }

    public TextField getSearch() {
        return search;
    }

    public Button getSettings_button() {
        return settings_button;
    }

    public Button getAbout_button() {
        return about_button;
    }

    public ScrollPane getMain() {
        return main;
    }

    public GridPane getSelectionGrid() {
        return selectionGrid.getPane();
    }

    public MainSearcher getSearcher() {
        return searcher;
    }

}
