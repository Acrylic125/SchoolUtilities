package com.acrylic.utils;

import com.acrylic.enums.Mode;
import javafx.animation.Transition;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class TransitionAnimation extends Transition {

    private final Mode mode;
    private final Consumer<Double> animation;

    public TransitionAnimation(@NotNull Duration duration, @NotNull Mode mode, @NotNull Consumer<Double> animation) {
        setCycleDuration(duration);
        this.mode = mode;
        this.animation = animation;
    }

    @NotNull
    public Mode getMode() {
        return mode;
    }

    @NotNull
    public Consumer<Double> getAnimation() {
        return animation;
    }

    @Override
    public void interpolate(double v) {
        animation.accept((mode == Mode.IN) ? v : (1 - v));
    }

    public TransitionAnimation cloneAsMode(@NotNull Mode mode) {
        return new TransitionAnimation(getCycleDuration(), mode, animation);
    }

}
