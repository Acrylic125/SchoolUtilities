package com.acrylic.searcher;

import com.acrylic.main.MenuRedirectOption;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public interface Searcher<T extends Searchable> {

    @NotNull
    Collection<T> getSearchFrom();

    default Collection<T> getOptionsByID(@NotNull String search) {
        List<T> options = new ArrayList<>();
        iterateOptionsByID(search, options::add);
        return options;
    }

    default void iterateOptionsByID(@NotNull String search, @NotNull Consumer<T> action) {
        search = search.toLowerCase(Locale.ROOT);
        for (T menuRedirectOption : getSearchFrom()) {
            if (menuRedirectOption.getIDPattern().matcher(search).find())
                action.accept(menuRedirectOption);
        }
    }

}
