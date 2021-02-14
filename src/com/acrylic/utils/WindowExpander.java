package com.acrylic.utils;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class WindowExpander {

    /**
     * The reference indicates whether the drag action should
     * expand or relocate the scene.
     */
    public enum Reference {
        RELOCATION, EXPAND, NONE,
        EA_P_20, EG_P_20, EF_P_20 //Ignore
    }

    private Collection<Node> relocationReferenceNodes = new ArrayList<>();
    private Reference reference = Reference.NONE;
    private int errorRadii = 5;

    public WindowExpander(@NotNull Stage stage) {
        this(stage.getScene());
        stage.setX(0);
    }

    public WindowExpander(@NotNull Scene scene) {
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
        });
        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {

            System.out.println("dragged " + event.getSceneX() + " " + event.getSceneY());
        });
        scene.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, event -> {

        });
        scene.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, event -> {
        });
    }

    public void setReference(@NotNull Reference reference) {
        this.reference = reference;
    }

    @NotNull
    public Reference getReference() {
        return reference;
    }

    public int getErrorRadii() {
        return errorRadii;
    }

    public void setErrorRadii(int errorRadii) {
        this.errorRadii = errorRadii;
    }

    public void addRelocationReferenceNode(@NotNull Node node) {
        this.relocationReferenceNodes.add(node);
    }

    public void addAllRelocationReferenceNodes(@NotNull Collection<? extends Node> nodes) {
        this.relocationReferenceNodes.addAll(nodes);
    }

    public void addAllRelocationReferenceNodes(@NotNull Node... nodes) {
        addAllRelocationReferenceNodes(Arrays.asList(nodes));
    }

    public void removeReferenceNode(@NotNull Node node) {
        this.relocationReferenceNodes.remove(node);
    }

    public void removeAllRelocationReferenceNode(@NotNull Collection<? extends Node> nodes) {
        this.relocationReferenceNodes.removeAll(nodes);
    }

    public void removeAllRelocationReferenceNodes(@NotNull Node... nodes) {
        addAllRelocationReferenceNodes(Arrays.asList(nodes));
    }

    public void setRelocationReferenceNodes(@NotNull Collection<Node> relocationReferenceNodes) {
        this.relocationReferenceNodes = relocationReferenceNodes;
    }

    @NotNull
    public Collection<Node> getRelocationReferenceNodes() {
        return relocationReferenceNodes;
    }

    private static class Handler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
           // System.out.println(((Scene) event.getSource()).getRoot());
        }
    }

}
