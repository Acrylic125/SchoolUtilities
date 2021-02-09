package com.acrylic.panes;

import com.acrylic.utils.FXUtils;
import com.acrylic.utils.GridMapper;
import com.acrylic.utils.ColumnRow;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A rigid grid pane ensures all it's children are of the
 * same size. This will allow for easier manipulation of
 * overflowing children.
 *
 * Do note that this is specifically for static sizes and
 * should not be modified.
 *
 * NOTE:
 * The default add, clear, remove node functions
 * does not modify the bases. This means that when
 * this is updated, ndoes added with #add will not
 * appear or nodes removed from getChildrent().remove()
 * will still appear. The base grid pane is used as
 * the direct display whereas this pane helps with the
 * organisation of these nodes.
 *
 * Use #addNodeToBasePane and #removeNodeFromBasePane
 * instead.
 *
 */
public class RigidOverflowGridPane extends GridPane {

    private final GridMapper gridMapper = new GridMapper(this);
    private final Map<Node, ColumnRow> baseNodeLocations = new HashMap<>();
    private int sizeX = 100, sizeY = 100;
    /**
     * If true, the nodes will adapt and overflow to the next
     * row or column based on the {@link com.acrylic.enums.UIFormatStyle}.
     */
    private boolean adaptOnResizeOverflow = true;
    private int baseColumns = 0, baseRows = 0;

    public RigidOverflowGridPane() {
        super.widthProperty().addListener((observableValue, oldValue, newValue) -> updateWidth(oldValue, newValue));
        super.heightProperty().addListener((observableValue, oldValue, newValue) -> updateHeight(oldValue, newValue));
    }

    private void updateWidth(Number oldValue, Number newValue) {
        System.out.println("Columns: " + getColumnsByWidth((Double) newValue));
    }

    private void updateHeight(Number oldValue, Number newValue) {
        System.out.println("Rows: " + getRowsByHeight((Double) newValue));
    }

    private double getRequiredWidthExpansion() {
        return getOccupiedWidth(baseColumns + 1);
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
        return (columns * sizeX) + (getVgap() * (columns - 1));
    }

    private int getColumnsByWidth(double width) {
        double v = getVgap();
        return (int) Math.floor((width + v) / (sizeX + v));
    }

    private double getRequiredHeightExpansion() {
        return getOccupiedHeight(baseRows + 1);
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
        return (rows * sizeY) + (getHgap() * (rows - 1));
    }

    private int getRowsByHeight(double height) {
        double h = getHgap();
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

    public GridMapper getGridMapper() {
        return gridMapper;
    }

    public Collection<Node> getNodes() {
        return baseNodeLocations.keySet();
    }

    @NotNull
    public Map<Node, ColumnRow> getBaseNodeLocations() {
        return baseNodeLocations;
    }

    /**
     * This adds the node to the base pane.
     *
     * @param node Node.
     * @param column Base column in the base pane.
     * @param row Base row in the base pane.
     */
    public void addNodeToBasePane(@NotNull Node node, int column, int row) {
        super.getChildren().add(node);
        addBaseNodeLocation(node, column, row);
        adoptNodeSize(node);
        baseColumns = Math.max(baseColumns, column);
        baseRows = Math.max(baseRows, row);
    }

    public void removeAllNodesFromBasePane(@NotNull Node... nodes) {
        super.getChildren().removeAll(nodes);
        for (Node node : nodes)
            removeBaseNodeLocation(node);
        recalculateBaseColumnsAndRows();
    }

    /**
     * Removes the node from the base pane.
     *
     * @param node Node.
     */
    public void removeNodeFromBasePane(@NotNull Node node) {
        super.getChildren().remove(node);
        removeBaseNodeLocation(node);
        recalculateBaseColumnsAndRows();
    }

    public void clearAllNodesFromBasePane() {
        super.getChildren().clear();
        baseNodeLocations.clear();
        recalculateBaseColumnsAndRows();
    }

    private void recalculateBaseColumnsAndRows() {
        baseColumns = 0;
        baseRows = 0;
        if (baseNodeLocations.size() > 0) {
            baseNodeLocations.forEach((node, columnRow) -> {
                baseColumns = Math.max(columnRow.getColumn(), baseColumns);
                baseRows = Math.max(columnRow.getRow(), baseRows);
            });
        }
    }

    public void readoptAllNodeSize() {
        for (Node node : getNodes())
            adoptNodeSize(node);
    }

    public void adoptNodeSize(@NotNull Node node) {
        if (node instanceof Region) {
            Region region = (Region) node;
            region.setPrefSize(sizeX, sizeY);
            FXUtils.setMinMaxSizeAsPref(region);
        }
    }

    private void addBaseNodeLocation(@NotNull Node node, int column, int row) {
        baseNodeLocations.put(node, new ColumnRow(column, row));
    }

    private void removeBaseNodeLocation(@NotNull Node node) {
        baseNodeLocations.remove(node);
    }

    @Nullable
    private ColumnRow getBaseNodeLocation(@NotNull Node node) {
        return baseNodeLocations.get(node);
    }

}
