package com.acrylic.searcher;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public interface Searchable {

    @NotNull
    Pattern getIDPattern();

}
