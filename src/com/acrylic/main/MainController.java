package com.acrylic.main;

import com.acrylic.utils.FXUtils;
import com.acrylic.panes.RigidGridPane;
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

    private final RigidGridPane selectionGrid = new RigidGridPane();
    private final MainSearcher searcher = new MainSearcher(this);

    @FXML
    private void initialize() {
        main.setPadding(new Insets(30));
        FXUtils.cloneSizeFrom(selectionGrid, main);
        FXUtils.setMinMaxSizeByFactorFromPref(selectionGrid, 1f, 2f);
        selectionGrid.setHgap(20);
        selectionGrid.setVgap(20);
        selectionGrid.setSizeX(200);
        selectionGrid.setSizeY(200);
        addOptionWithGrid(new CiteOption(), 0, 0);
        addOptionWithGrid(new QuickLinksOption(), 0, 1);
        addOptionWithGrid(new GPACalculatorOption(), 1, 0);
        selectionGrid.init();
        main.setContent(selectionGrid);
        main.setFitToHeight(true);
        main.setFitToWidth(true);
        setScrollingSpeed(1.3f);
        searcher.initSearchBar();
    }

    public void addOptionWithGrid(@NotNull MenuRedirectOption option, int x, int y) {
        searcher.addOption(option);
        selectionGrid.add(option.getButton(), x, y);
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
        return selectionGrid;
    }

    public MainSearcher getSearcher() {
        return searcher;
    }

}
