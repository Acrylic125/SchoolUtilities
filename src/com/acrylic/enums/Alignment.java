package com.acrylic.enums;

import org.jetbrains.annotations.NotNull;

/**
 * [ top-left | top-center | top-right |
 * center-left | center | center-right |
 * bottom-left | bottom-center | bottom-right |
 * baseline-left | baseline-center | baseline-right ]
 *
 * https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#labeled
 */
public enum Alignment {
    TOP_LEFT("top-left"),
    TOP_CENTER("top-center"),
    TOP_RIGHT("top-right"),
    CENTER_LEFT("center-left"),
    CENTER("center"),
    CENTER_RIGHT("center-right"),
    BOTTOM_LEFT("bottom-left"),
    BOTTOM_CENTER("bottom-center"),
    BOTTOM_RIGHT("bottom-right"),
    BASELINE_LEFT("baseline-left"),
    BASELINE("baseline"),
    BASELINE_RIGHT("baseline-right");

    private final String name;

    Alignment(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getName() {
         return name;
    }
}
