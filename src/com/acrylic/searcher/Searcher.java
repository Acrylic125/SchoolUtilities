package com.acrylic.searcher;

import com.acrylic.utils.CancellableIterated;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public interface Searcher<T extends Searchable> {

    @NotNull
    Collection<T> getSearchFrom();

    default Collection<T> getOptionsByID(@NotNull String search) {
        List<T> options = new ArrayList<>();
        iterateOptionsByID(search, options::add);
        return options;
    }

    default void iterateOptionsByID(@NotNull String search, @NotNull CancellableIterated<T> action) {
        search = search.toUpperCase(Locale.ROOT);
        for (T menuRedirectOption : getSearchFrom()) {
            if (menuRedirectOption.matchID(search))
                if (action.accept(menuRedirectOption))
                    return;
        }
    }

}
