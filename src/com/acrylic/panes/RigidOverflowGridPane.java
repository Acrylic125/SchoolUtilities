package com.acrylic.panes;

import com.acrylic.utils.FXUtils;
import com.acrylic.utils.IntCoord2D;
import com.acrylic.utils.GridMapper;
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
 * #update is called, ndoes added with #add will not
 * appear or nodes removed from getChildrent().remove()
 * will still appear. The base grid pane is used as
 * the direct display whereas this pane helps with the
 * organisation of these nodes.
 *
 * Use #addNodeToBasePane and #removeNodeFromBasePane
 * instead.
 */
public class RigidOverflowGridPane extends GridPane {

    private final GridMapper gridMapper = new GridMapper(this);
    private final Map<Node, IntCoord2D> baseNodeLocations = new HashMap<>();
    private int sizeX = 100, sizeY = 100;
    /**
     * If true, the nodes will adapt and overflow to the next
     * row or column based on the {@link com.acrylic.enums.UIFormatStyle}.
     */
    private boolean adaptOnResizeOverflow = true;
    private int baseColumns = 0, baseRows = 0;

    public RigidOverflowGridPane() {
        init();
    }

    /**
     * Initializer.
     */
    private void init() {
        //TODO:
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
    public Map<Node, IntCoord2D> getBaseNodeLocations() {
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
    }

    /**
     * Removes the node from the base pane.
     *
     * @param node Node.
     */
    public void removeNodeFromBasePane(@NotNull Node node) {
        super.getChildren().remove(node);
        removeBaseNodeLocation(node);
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

    public void update() {

    }

    private void addBaseNodeLocation(@NotNull Node node, int column, int row) {
        baseNodeLocations.put(node, new IntCoord2D(column, row));
    }

    private void removeBaseNodeLocation(@NotNull Node node) {
        baseNodeLocations.remove(node);
    }

    @Nullable
    private IntCoord2D getBaseNodeLocation(@NotNull Node node) {
        return baseNodeLocations.get(node);
    }

}
