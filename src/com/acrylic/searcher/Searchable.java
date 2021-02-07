package com.acrylic.searcher;

import org.ahocorasick.trie.Trie;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface Searchable {

    @NotNull
    Trie getIDs();

    @NotNull
    String[] getIDArray();

    default boolean matchID(@NotNull String id) {
        if (getIDs().containsMatch(id))
            return true;
        for (String s : getIDArray()) {
            if (s.contains(id))
                return true;
        }
        return false;
    }

    static Trie convertToIDTrie(@NotNull String[] arr) {
        return Trie.builder()
                .ignoreCase()
                .addKeywords(arr)
                .stopOnHit()
                .build();
    }

    static String[] convertToIDArray(@NotNull String[] arr) {
        String[] idArray = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
            idArray[i] = arr[i].toUpperCase(Locale.ROOT);
        return idArray;
    }

}
