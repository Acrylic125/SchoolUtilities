package com.acrylic.windowexpander;

import com.acrylic.enums.UIDirection;
import com.acrylic.utils.MathUtils;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * This class represents a window expander handler.
 *
 * This enables scenes to be movable and expandable based
 * on it's current {@link Reference}.
 */
public abstract class WindowExpander {

    public static final int CHILD_THRESHOLD = 10;
    public static final int RELATIVE_VALUE = 4;

    public enum Setting {
        /**
         * Only involve the specified node.
         */
        THIS_ONLY,
        /**
         * Only involve the first child of the specified node.
         */
        FIRST_CHILD_ONLY,
        /**
         * Involves the specified node and the first child of it.
         */
        THIS_AND_FIRST_CHILD,
        /**
         * Only involves all the children of the specified node.
         *
         * This means that the child of the child... etc also
         * gets involved in this expander listener.
         */
        ALL_CHILDREN_ONLY,
        ALL
    }

    /**
     * The reference indicates whether the drag action should
     * expand or relocate the scene.
     */
    public enum Reference {
        /**
         * If the window expander is marked as RELOCATION,
         * when the cursor is dragged, it will relocate the
         * window based on the cursor.
         */
        RELOCATION,
        /**
         * If the window expander is marked as EXPAND, when
         * the cursor is dragged, it will expand the window.
         */
        EXPAND,
        NONE
    }

    /**
     * The relocationPredicate determines when the window expander
     * should switch to {@link Reference#RELOCATION}.
     */
    private Predicate<MouseEvent> relocationPredicate;
    /**
     * The reference will update based on the location of the
     * cursor.
     */
    private Reference reference = Reference.NONE;
    /**
     * The error bound ensures less sensitive cursor
     * checks.
     */
    private int errorBound = 10;
    private boolean isDragging = false;
    protected double cursorOffsetX = 0, cursorOffsetY = 0;
    protected double screenX = 0, screenY = 0;
    protected double initWidth = 0, initHeight = 0;
    protected UIDirection expandDirection;

    private void includeListenerRecursively(int acc, int limit, @NotNull Node node, boolean includeFIrst) {
        acc++;
        if (includeFIrst || acc != 1)
            includeListener(node);
        if (acc >= limit)
            return;
        if (node instanceof Parent) {
            Parent parent = (Parent) node;
            for (Node child : parent.getChildrenUnmodifiable())
                includeListenerRecursively(acc, limit, child, includeFIrst);
        }
    }

    public void includeListener(@NotNull Node node) {
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed);
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onMouseDrag);
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onMouseReleased);
        node.addEventHandler(MouseEvent.MOUSE_MOVED, this::onMouseMove);
    }

    private void includeListener(@NotNull Node node, @NotNull Setting setting, int thresholdBonus) {
        switch (setting) {
            case FIRST_CHILD_ONLY -> includeListenerRecursively(0, thresholdBonus + 1, node, false);
            case THIS_AND_FIRST_CHILD -> includeListenerRecursively(0, thresholdBonus + 1, node, true);
            case ALL_CHILDREN_ONLY -> includeListenerRecursively(0, thresholdBonus + CHILD_THRESHOLD, node, false);
            case ALL -> includeListenerRecursively(0, thresholdBonus + CHILD_THRESHOLD, node, true);
            default -> includeListener(node);
        }
    }

    public void includeListener(@NotNull Node node, @NotNull Setting setting) {
        includeListener(node, setting, 0);
    }

    public void includeListener(@NotNull Scene scene, @NotNull Setting setting) {
        includeListener(scene.getRoot(), setting, 1);
    }

    protected abstract Bounds getSceneBounds();

    private boolean isRelocationReference(MouseEvent event) {
        return relocationPredicate != null && relocationPredicate.test(event);
    }

    private boolean doExpansionCheck(MouseEvent event) {
        Bounds bounds = getSceneBounds();
        double mouseX = event.getScreenX(), mouseY = event.getScreenY();
        expandDirection = getDirectionBy(mouseX, mouseY, bounds, errorBound);
        if (expandDirection == null)
            return false;
        double d = MathUtils.squared(errorBound * 3);
        double x1 = bounds.getMinX(), y1 = bounds.getMinY(),
                x2 = bounds.getMaxX(), y2 = bounds.getMaxY();
        if (MathUtils.distanceSquared(mouseX, mouseY, x1, y1) <= d) {
            expandDirection = UIDirection.NORTH_WEST;
        } else if (MathUtils.distanceSquared(mouseX, mouseY, x1, y2) <= d) {
            expandDirection = UIDirection.SOUTH_WEST;
        } else if (MathUtils.distanceSquared(mouseX, mouseY, x2, y1) <= d) {
            expandDirection = UIDirection.NORTH_EAST;
        } else if (MathUtils.distanceSquared(mouseX, mouseY, x2, y2) <= d) {
            expandDirection = UIDirection.SOUTH_EAST;
        }
        setCursor(expandDirection.getDirectionCursor());
        return true;
    }

    protected abstract void setCursor(@NotNull Cursor cursor);

    protected void onMousePressed(MouseEvent event) {
        Bounds bounds = getSceneBounds();
        cursorOffsetX = event.getScreenX() - bounds.getMinX();
        cursorOffsetY = event.getScreenY() - bounds.getMinY();
        screenX = event.getScreenX();
        screenY = event.getScreenY();
        initWidth = getWidth();
        initHeight = getHeight();
    }

    public abstract double getWidth();

    public abstract double getHeight();

    private void onMouseReleased(MouseEvent event) {
        isDragging = false;
    }

    private void onMouseDrag(MouseEvent event) {
        if (!isDragging)
            onMousePressed(event);
        isDragging = true;
        onMouseMove(event);
        switch (reference) {
            case RELOCATION -> moveTo(event, event.getScreenX() - cursorOffsetX, event.getScreenY() - cursorOffsetY);
            case EXPAND -> {
                if (expandDirection != null) {
                    //System.out.println(event.getX() + " " + cursorOffsetX);
                    expandTo(event, event.getScreenX(), event.getScreenY());
                }
            }
        }
    }

    protected abstract void moveTo(MouseEvent event, double x, double y);

    protected abstract void expandTo(MouseEvent event, double x, double y);

    private void onMouseMove(MouseEvent event) {
        if (!isDragging) {
            if (doExpansionCheck(event)) {
                reference = Reference.EXPAND;
            } else {
                if (reference.equals(Reference.EXPAND))
                    setCursor(Cursor.DEFAULT);
                if (isRelocationReference(event)) {
                    reference = Reference.RELOCATION;
                } else {
                    reference = Reference.NONE;
                }
            }
        }
    }

    public void setReference(@NotNull Reference reference) {
        this.reference = reference;
    }

    @NotNull
    public Reference getReference() {
        return reference;
    }

    public int getErrorBound() {
        return errorBound;
    }

    public void setErrorBound(int errorBound) {
        this.errorBound = errorBound;
    }

    @Nullable
    public Predicate<MouseEvent> getRelocationPredicate() {
        return relocationPredicate;
    }

    public void relocateIf(@Nullable Predicate<MouseEvent> relocationPredicate) {
        this.relocationPredicate = relocationPredicate;
    }

    public void relocateIfContactWithNodes(@NotNull Node... nodes) {
        relocateIfContactWithNodes(Arrays.asList(nodes));
    }

    public void relocateIfContactWithNodes(@NotNull Collection<? extends Node> nodes) {
        relocateIf(event -> {
            double x = event.getScreenX(), y = event.getScreenY();
            for (Node node : nodes) {
                Bounds bounds = node.localToScreen(node.getBoundsInLocal());
                if (MathUtils.isPointInRegion(x, y, bounds, errorBound))
                    return true;
            }
            return false;
        });
    }

    public boolean isDragging() {
        return isDragging;
    }

    @Nullable
    private static UIDirection getDirectionBy(double x, double y, @NotNull Bounds bounds, double errorBound) {
        return getDirectionBy(x, y, bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY(), errorBound);
    }

    @Nullable
    private static UIDirection getDirectionBy(double x, double y, double regionX1, double regionY1, double regionX2, double regionY2, double errorBound) {
        if (MathUtils.isNumberInRange(x, regionX1, errorBound))
            return UIDirection.WEST;
        else if (MathUtils.isNumberInRange(x, regionX2, errorBound))
            return UIDirection.EAST;
        else if (MathUtils.isNumberInRange(y, regionY1, errorBound))
            return UIDirection.NORTH;
        else if (MathUtils.isNumberInRange(y, regionY2, errorBound))
            return UIDirection.SOUTH;
        else
            return null;
    }

}
