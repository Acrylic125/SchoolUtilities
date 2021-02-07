package com.acrylic.utils;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface CancellableIterated<T> {

    /**
     *
     * @param t type
     * @return Returns whether the current iteration should stop.
     */
    boolean accept(@NotNull T t);

}
