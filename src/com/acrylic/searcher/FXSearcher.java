package com.acrylic.searcher;

import com.acrylic.utils.GridMapper;
import org.jetbrains.annotations.NotNull;

public interface FXSearcher<T extends FXSearchable> extends Searcher<T> {

    default void mapToGridByID(@NotNull String search, @NotNull GridMapper gridMapper) {
        gridMapper.reset();
        iterateOptionsByID(search, t -> gridMapper.singleMapWith(t.getNode()));
    }

}
