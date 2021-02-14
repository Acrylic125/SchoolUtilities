package com.acrylic.utils;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
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

public class WindowExpander {

    public static final int CHILD_THRESHOLD = 10;

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
    private int errorBound = 5;

    public WindowExpander(@NotNull Stage stage, @NotNull Setting setting) {
        this(stage.getScene(), setting);
    }

    public WindowExpander(@NotNull Scene scene, @NotNull Setting setting) {
        System.out.println(scene.getClass());
        includeListener(scene, setting);
    }

    //Setting Helpers.
    private void includeListenerRecursively(int acc, int limit, @NotNull Node node, boolean includeFIrst) {
        acc++;
        System.out.println(node.getClass());
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

    private void onMousePressed(MouseEvent event) {

    }

    private void onMouseDrag(MouseEvent event) {
        if (relocationPredicate == null || relocationPredicate.test(event)) {

        }
        // System.out.println("dragged " + event.getSceneX() + " " + event.getSceneY());
    }

    private void onMouseMove(MouseEvent event) {

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
            double x = event.getX(), y = event.getY();
            for (Node node : nodes) {
                System.out.println(" Loc : " + x + " " + y);
                Bounds bounds = node.localToScreen(node.getBoundsInLocal());
                System.out.println(bounds.getMinX() + " " + bounds.getMinY());
            }
            return false;
        });
    }

}
