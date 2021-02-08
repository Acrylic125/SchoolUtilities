package com.acrylic.utils;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class RealignmentGridPane extends GridPane {

    private GridMapper gridMapper = new GridMapper(this);

    @Override
    public void add(Node node, int i, int i1) {
        super.add(node, i, i1);
    }

    @Override
    public void add(Node node, int i, int i1, int i2, int i3) {
        super.add(node, i, i1, i2, i3);
    }

    public void init() {

        System.out.println(getBoundsInParent());
        System.out.println(getColumnCount());
        widthProperty().addListener((observableValue, oldValue, newValue) -> {

        });
    }

    public void setGridMapper(@NotNull GridMapper gridMapper) {
        this.gridMapper = gridMapper;
    }

    @NotNull
    public GridMapper getGridMapper() {
        return gridMapper;
    }
}
