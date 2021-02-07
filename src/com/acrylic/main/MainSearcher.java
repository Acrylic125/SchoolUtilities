package com.acrylic.main;

import com.acrylic.searcher.FXSearcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public final class MainSearcher implements FXSearcher<MenuRedirectOption> {

    private final MainController mainController;
    private final Collection<MenuRedirectOption> menuRedirectOptions = new ArrayList<>();

    public MainSearcher(@NotNull MainController mainController) {
        this.mainController = mainController;
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
