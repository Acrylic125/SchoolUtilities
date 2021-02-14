package com.acrylic.fxobjects;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface FXNode {

    @NotNull
    Parent getRoot();

    default Collection<Node> getChildrenUnmodifiable() {
        return getRoot().getChildrenUnmodifiable();
    }

    default void setLayout(double x, double y) {
        Parent parent = getRoot();
        parent.setLayoutX(x);
        parent.setLayoutY(y);
    }

    default void addToGrid(@NotNull GridPane gridPane, int x, int y) {
        gridPane.add(getRoot(), x, y);
    }

    default void addToGrid(@NotNull GridPane gridPane, int x, int y, int x2, int y2) {
        gridPane.add(getRoot(), x, y, x2, y2);
    }

}
