package com.acrylic.main;

import com.acrylic.searcher.FXSearcher;
import com.acrylic.utils.GridMapper;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public final class MainSearcher implements FXSearcher<MenuRedirectOption> {

    private final MainController mainController;
    private final Collection<MenuRedirectOption> menuRedirectOptions = new ArrayList<>();

    public MainSearcher(@NotNull MainController mainController) {
        this.mainController = mainController;
    }

    public void initSearchBar() {
        TextField searchBar = mainController.getSearch();
        searchBar.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                mainController.getSelectionGrid().getChildren().clear();
                mapToGridByID(searchBar.getText(), new GridMapper(mainController.getSelectionGrid()).setMaxX(2).setMaxY(10));
            }
        });
    }

    @NotNull
    public MainController getMainController() {
        return mainController;
    }

    public void addOption(@NotNull MenuRedirectOption option) {
        menuRedirectOptions.add(option);
    }

    @Override
    public @NotNull Collection<MenuRedirectOption> getSearchFrom() {
        return menuRedirectOptions;
    }
}
