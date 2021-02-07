package com.acrylic.utils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

public final class GridMapper {

    private final GridPane gridPane;
    private int maxX = 3, maxY = 3;
    private int initialX = 0, initialY = 0;
    private int currentX = 0, currentY = 0;
    private int incrementX = 1, incrementY = 1;
    private UIFormatStyle startFrom = UIFormatStyle.HORIZONTAL;

    public GridMapper(@NotNull GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public GridMapper reset() {
        currentX = 0;
        currentY = 0;
        return this;
    }

    @NotNull
    public GridPane getGridPane() {
        return gridPane;
    }

    public int getMaxX() {
        return maxX;
    }

    public GridMapper setMaxX(int maxX) {
        this.maxX = maxX;
        return this;
    }

    public int getMaxY() {
        return maxY;
    }

    public GridMapper setMaxY(int maxY) {
        this.maxY = maxY;
        return this;
    }

    public int getInitialX() {
        return initialX;
    }

    public GridMapper setInitialX(int initialX) {
        this.initialX = initialX;
        this.currentX = initialX;
        return this;
    }

    public int getInitialY() {
        return initialY;
    }

    public GridMapper setInitialY(int initialY) {
        this.initialY = initialY;
        this.currentY = initialY;
        return this;
    }

    public int getCurrentX() {
        return currentX;
    }

    public GridMapper setCurrentX(int currentX) {
        this.currentX = currentX;
        return this;
    }

    public int getCurrentY() {
        return currentY;
    }

    public GridMapper setCurrentY(int currentY) {
        this.currentY = currentY;
        return this;
    }

    public int getIncrementX() {
        return incrementX;
    }

    public GridMapper setIncrementX(int incrementX) {
        this.incrementX = incrementX;
        return this;
    }

    public int getIncrementY() {
        return incrementY;
    }

    public GridMapper setIncrementY(int incrementY) {
        this.incrementY = incrementY;
        return this;
    }

    public GridMapper setStartFrom(@NotNull UIFormatStyle startFrom) {
        this.startFrom = startFrom;
        return this;
    }

    public boolean singleMapWith(@NotNull Node node) {
        if (currentX > maxX && currentY > maxY) {
            gridPane.add(node, currentX, currentY);
            if (startFrom == UIFormatStyle.HORIZONTAL) {
                currentX += incrementX;
                if (currentX > maxX)
                    nextY();
            } else {
                currentY += incrementY;
                if (currentY > maxY)
                    nextX();
            }
            return true;
        }
        return false;
    }

    public void nextX() {
        currentY = initialY;
        currentX += incrementX;
    }

    public void nextY() {
        currentX = initialX;
        currentY += incrementY;
    }

}
