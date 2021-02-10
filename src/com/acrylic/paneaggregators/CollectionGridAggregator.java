package com.acrylic.paneaggregators;

import com.acrylic.enums.UIFormatStyle;
import com.acrylic.utils.GridMapper;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CollectionGridAggregator implements PaneAggregator {

    private final GridPane gridPane;
    private final GridMapper gridMapper;
    private int sizeX = 100, sizeY = 100;
    /**
     * If true, the nodes will adapt and overflow to the next
     * row or column based on the {@link com.acrylic.enums.UIFormatStyle}.
     */
    private boolean adaptOnResizeOverflow = true;
    private int baseColumns = 0, baseRows = 0;

    public CollectionGridAggregator(@NotNull GridPane gridPane) {
        this.gridPane = gridPane;
        this.gridMapper = new GridMapper(this.gridPane);
        gridPane.widthProperty().addListener((observableValue, oldValue, newValue) -> update());
        gridPane.heightProperty().addListener((observableValue, oldValue, newValue) -> update());
    }

    public void update() {
        if (adaptOnResizeOverflow) {
            switch (getFormatStyle()) {
                case HORIZONTAL -> {
                    gridMapper.setMaxColumns(getColumnsByWidth());
                    gridMapper.setMaxRows();
                }
                case VERTICAL -> {
                    gridMapper.setMaxColumns();
                    gridMapper.setMaxRows(getRowsByHeight());
                }
            }
            Collection<Node> nodes = new ArrayList<>(gridPane.getChildren());
            gridPane.getChildren().clear();
            gridMapper.map(nodes);
        }
    }

    public void setFormatStyle(@NotNull UIFormatStyle uiFormatStyle) {
        gridMapper.setFormatStyle(uiFormatStyle);
    }

    @NotNull
    public UIFormatStyle getFormatStyle() {
        return gridMapper.getFormatStyle();
    }

    /**
     *
     * @return The occupied width of all the nodes.
     */
    private double getBaseOccupiedWidth() {
        return getOccupiedWidth(baseColumns);
    }

    /**
     *
     * @param columns Columns.
     * @return The width occupied by the specified amount
     * of columns.
     */
    private double getOccupiedWidth(int columns) {
        return (columns * sizeX) + (gridPane.getVgap() * (columns - 1));
    }

    private int getColumnsByWidth() {
        return Math.max(getColumnsByWidth(gridPane.getWidth()), baseColumns + 1);
    }

    private int getColumnsByWidth(double width) {
        double v = gridPane.getVgap();
        return (int) Math.floor((width + v) / (sizeX + v));
    }

    /**
     *
     * @return The occupied height of all the nodes.
     */
    private double getBaseOccupiedHeight() {
        return getOccupiedHeight(baseRows);
    }

    /**
     *
     * @param rows Rows.
     * @return The height occupied by the specified amount
     * of rows.
     */
    private double getOccupiedHeight(int rows) {
        return (rows * sizeY) + (gridPane.getHgap() * (rows - 1));
    }

    private int getRowsByHeight() {
        return Math.max(getRowsByHeight(gridPane.getHeight()), baseRows + 1);
    }

    private int getRowsByHeight(double height) {
        double h = gridPane.getHgap();
        return (int) Math.floor((height + h) / (sizeY + h));
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public boolean isAdaptOnResizeOverflow() {
        return adaptOnResizeOverflow;
    }

    public void setAdaptOnResizeOverflow(boolean adaptOnResizeOverflow) {
        this.adaptOnResizeOverflow = adaptOnResizeOverflow;
    }

    public int getBaseColumns() {
        return baseColumns;
    }

    public void setBaseColumns(int baseColumns) {
        this.baseColumns = baseColumns;
    }

    public int getBaseRows() {
        return baseRows;
    }

    public void setBaseRows(int baseRows) {
        this.baseRows = baseRows;
    }

    @Override
    public @NotNull GridPane getPane() {
        return gridPane;
    }

    @Override
    public @NotNull Collection<Node> getNodes() {
        return gridPane.getChildren();
    }

    @NotNull
    public GridMapper getGridMapper() {
        return gridMapper;
    }
}
