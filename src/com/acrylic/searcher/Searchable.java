package com.acrylic.searcher;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public interface Searchable {

    @NotNull
    String[] getIDs();

    default boolean matchID(@NotNull String id) {
        for (String s : getIDs()) {
            System.out.println(s + " " + id);
            if (s.contains(id) || id.contains(s))
                return true;
        }
        return false;
    }

}
