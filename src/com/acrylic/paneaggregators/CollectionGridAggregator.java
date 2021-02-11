package com.acrylic.paneaggregators;

import com.acrylic.enums.UIFormatStyle;
import com.acrylic.utils.FXUtils;
import com.acrylic.utils.GridMapper;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CollectionGridAggregator implements PaneAggregator {

    private final GridPane gridPane;
    private final GridMapper gridMapper;
    private int sizeX = 100, sizeY = 100;
    /**
     * If true, the nodes will adapt and overflow to the next
     * row or column based on the {@link com.acrylic.enums.UIFormatStyle}.
     */
    private boolean adaptOnResizeOverflow = true;
    private int base = 0;

    public CollectionGridAggregator(@NotNull GridPane gridPane) {
        this.gridPane = gridPane;
        this.gridMapper = new GridMapper(this.gridPane);
        this.gridMapper.setMappedNodeAction((node, column, row) -> {
            switch (getFormatStyle()) {
                case HORIZONTAL -> this.gridPane.setMinSize(getOccupiedWidth(column), getOccupiedHeight(row + 1));
                case VERTICAL -> this.gridPane.setMinSize(getOccupiedWidth(column + 1), getOccupiedHeight(row));
            }
        });
        gridPane.widthProperty().addListener((observableValue, oldValue, newValue) -> update());
        gridPane.heightProperty().addListener((observableValue, oldValue, newValue) -> update());
    }

    public void update() {
        if (adaptOnResizeOverflow) {
            int columns = gridMapper.getMaxColumns(), rows = gridMapper.getMaxRows();
            switch (getFormatStyle()) {
                case HORIZONTAL -> {
                    columns = getColumnsByWidth();
                    rows = Integer.MAX_VALUE;
                }
                case VERTICAL -> {
                    columns = Integer.MAX_VALUE;
                    rows = getRowsByHeight();
                }
            }
            if (columns != gridMapper.getMaxColumns() || rows != gridMapper.getMaxRows()) {
                gridMapper.setMaxColumns(columns);
                gridMapper.setMaxRows(rows);
                gridMapper.mapChildren();
            }
        }
    }

    public void setFormatStyle(@NotNull UIFormatStyle uiFormatStyle, int base) {
        gridMapper.setFormatStyle(uiFormatStyle);
        this.base = base;
    }

    @NotNull
    public UIFormatStyle getFormatStyle() {
        return gridMapper.getFormatStyle();
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }

    /**
     *
     * @return The occupied width of all the nodes.
     */
    private double getBaseOccupiedWidth() {
        return getOccupiedWidth(base);
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
        return Math.max(getColumnsByWidth(gridPane.getWidth()), base);
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
        return getOccupiedHeight(base);
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
        return Math.max(getRowsByHeight(gridPane.getHeight()), base);
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
        adoptSizeToAllNodes();
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
        adoptSizeToAllNodes();
    }

    public boolean isAdaptOnResizeOverflow() {
        return adaptOnResizeOverflow;
    }

    public void setAdaptOnResizeOverflow(boolean adaptOnResizeOverflow) {
        this.adaptOnResizeOverflow = adaptOnResizeOverflow;
    }

    public void setVgap(double vGap) {
        gridPane.setVgap(vGap);
        update();
    }

    public void setHgap(double hGap) {
        gridPane.setHgap(hGap);
        update();
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

    public void adoptSizeToNode(@NotNull Node node) {
        if (node instanceof Region) {
            Region region = (Region) node;
            region.setPrefSize(getSizeX(), getSizeY());
            FXUtils.setMinMaxSizeAsPref(region);
        }
    }

    public void adoptSizeToAllNodes() {
        for (Node child : gridPane.getChildren())
            adoptSizeToNode(child);
    }

    public void addNode(@NotNull Node node) {
        adoptSizeToNode(node);
        gridMapper.singleMapWith(node);
    }

}
