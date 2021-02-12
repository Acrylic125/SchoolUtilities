package com.acrylic.main;

import com.acrylic.cite.CiteOption;
import com.acrylic.paneaggregators.CollectionGridAggregator;
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
    @FXML private Button settingsButton;
    @FXML private Button aboutButton;
    @FXML private ScrollPane main;

    private final CollectionGridAggregator selectionGrid = new CollectionGridAggregator(new GridPane());
    private final MainSearcher searcher = new MainSearcher(this);

    @FXML
    private void initialize() {
        GridPane gridPane = selectionGrid.getPane();
        main.setPadding(new Insets(30));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        selectionGrid.setSizeX(200);
        selectionGrid.setSizeY(200);
        addOptionWithGrid(new CiteOption());
        addOptionWithGrid(new QuickLinksOption());
        addOptionWithGrid(new GPACalculatorOption());
        main.setContent(gridPane);
        main.setFitToHeight(true);
        main.setFitToWidth(true);
        setScrollingSpeed(0.2f);
        searcher.initSearchBar();
    }

    public void addOptionWithGrid(@NotNull MenuRedirectOption option) {
        searcher.addOption(option);
        selectionGrid.addNode(option.getButton());
    }

    private void setScrollingSpeed(float speed) {
        main.getContent().setOnScroll(event -> main.setVvalue(main.getVvalue() + -((event.getDeltaY() / Math.abs(event.getDeltaY())) * speed)));
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

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getAboutButton() {
        return aboutButton;
    }

    public ScrollPane getMain() {
        return main;
    }

    public GridPane getSelectionGrid() {
        return selectionGrid.getPane();
    }

    public CollectionGridAggregator getSelectionGridAggregator() {
        return selectionGrid;
    }

    public MainSearcher getSearcher() {
        return searcher;
    }

}
