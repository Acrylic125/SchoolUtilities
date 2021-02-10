package com.acrylic.utils;

import com.acrylic.enums.UIFormatStyle;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class GridMapper {

    private final GridPane gridPane;
    private int maxColumns = Integer.MAX_VALUE, maxRows = Integer.MAX_VALUE;
    private int initialColumn = 0, initialRow = 0;
    private int currentColumn = 0, currentRow = 0;
    private int incrementColumn = 1, incrementRow = 1;
    private UIFormatStyle formatStyle = UIFormatStyle.HORIZONTAL;

    public GridMapper(@NotNull GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public GridMapper reset() {
        currentColumn = 0;
        currentRow = 0;
        return this;
    }

    @NotNull
    public GridPane getGridPane() {
        return gridPane;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public GridMapper setMaxColumns(int maxColumns) {
        this.maxColumns = maxColumns;
        return this;
    }

    public GridMapper setMaxColumns() {
        return setMaxColumns(Integer.MAX_VALUE);
    }

    public int getMaxRows() {
        return maxRows;
    }

    public GridMapper setMaxRows(int maxRows) {
        this.maxRows = maxRows;
        return this;
    }

    public GridMapper setMaxRows() {
        return setMaxRows(Integer.MAX_VALUE);
    }

    public int getInitialColumn() {
        return initialColumn;
    }

    public GridMapper setInitialColumn(int initialColumn) {
        this.initialColumn = initialColumn;
        this.currentColumn = initialColumn;
        return this;
    }

    public int getInitialRow() {
        return initialRow;
    }

    public GridMapper setInitialRow(int initialRow) {
        this.initialRow = initialRow;
        this.currentRow = initialRow;
        return this;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public GridMapper setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
        return this;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public GridMapper setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
        return this;
    }

    public int getIncrementColumn() {
        return incrementColumn;
    }

    public GridMapper setIncrementColumn(int incrementColumn) {
        this.incrementColumn = incrementColumn;
        return this;
    }

    public int getIncrementRow() {
        return incrementRow;
    }

    public GridMapper setIncrementRow(int incrementRow) {
        this.incrementRow = incrementRow;
        return this;
    }

    @NotNull
    public UIFormatStyle getFormatStyle() {
        return formatStyle;
    }

    public GridMapper setFormatStyle(@NotNull UIFormatStyle formatStyle) {
        this.formatStyle = formatStyle;
        return this;
    }

    public void map(@NotNull Collection<? extends Node> nodes) {
        reset();
        for (Node node : nodes)
            singleMapWith(node);
    }

    public boolean singleMapWith(@NotNull Node node) {
        if (currentColumn <= maxColumns && currentRow <= maxRows) {
            gridPane.add(node, currentColumn, currentRow);
            if (formatStyle == UIFormatStyle.HORIZONTAL) {
                currentColumn += incrementColumn;
                if (currentColumn >= maxColumns)
                    nextY();
            } else {
                currentRow += incrementRow;
                if (currentRow >= maxRows)
                    nextX();
            }
            return false;
        }
        return true;
    }

    public void nextX() {
        currentRow = initialRow;
        currentColumn += incrementColumn;
    }

    public void nextY() {
        currentColumn = initialColumn;
        currentRow += incrementRow;
    }

}
