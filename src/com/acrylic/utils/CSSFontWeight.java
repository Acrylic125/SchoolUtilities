package com.acrylic.utils;

import org.jetbrains.annotations.NotNull;

public enum CSSFontWeight {

    BOLD("bold"), NORMAL("normal");

    private final String asCSS;

    CSSFontWeight(@NotNull String asCSS) {
        this.asCSS = asCSS;
    }

    public String getAsCSS() {
        return asCSS;
    }
}
