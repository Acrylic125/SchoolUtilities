package com.acrylic.cache;

import com.acrylic.sections.AbstractSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class SectionMap<T extends AbstractSection> {

    private final Map<Class<?>, T> map;

    public SectionMap() {
        this(new HashMap<>());
    }

    public SectionMap(@NotNull Map<Class<?>, T> map) {
        this.map = map;
    }

    @NotNull
    public Map<Class<?>, T> getMap() {
        return map;
    }

    @NotNull
    public T getSectionOrDefault(@NotNull T section) {
        return getSectionOrDefault(section.getClass(), section);
    }

    @NotNull
    public T getSectionOrDefault(@NotNull Class<?> clazz, @NotNull T section) {
        return map.getOrDefault(clazz, section);
    }

    @Nullable
    public T getSection(@NotNull Class<?> clazz) {
        return map.get(clazz);
    }

    public void cache(@NotNull T section) {
        map.put(section.getClass(), section);
    }

    public void remove(@NotNull T section) {
        remove(section.getClass());
    }

    public void remove(@NotNull Class<?> clazz) {
        map.remove(clazz);
    }

}
