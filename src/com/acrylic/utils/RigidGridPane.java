package com.acrylic.utils;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.NotNull;

public class RigidGridPane extends GridPane {

    private GridMapper gridMapper = new GridMapper(this);
    private double sizeX = 100, sizeY = 100;
    private boolean reformatOverflow = true;

    @Override
    public void add(Node node, int x, int y) {
        super.add(node, x, y);
        if (node instanceof Region) {
            Region region = (Region) node;
            region.setPrefSize(sizeX, sizeY);
            FXUtils.setMinMaxSizeAsPref(region);
        }
    }

    @Override
    public void add(Node node, int x, int y, int i2, int i3) {
        throw new UnsupportedOperationException("This is not supported!");
    }

    public void init() {
        System.out.println(calculateBaseGridX() + " " + calculateBaseGridY());
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

    public double getSizeX() {
        return sizeX;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
    }

    public double getBaseGridX() {
        return calculateBaseGridX();
    }

    public double getBaseGridY() {
        return calculateBaseGridY();
    }

    private double calculateBaseGridX() {
        int count = getColumnCount();
        return (count * sizeX) + ((count - 1) * getVgap());
    }

    private double calculateBaseGridY() {
        int count = getRowCount();
        return (count * sizeY) + ((count - 1) * getHgap());
    }

    public boolean isReformatOverflow() {
        return reformatOverflow;
    }

    public void setReformatOverflow(boolean reformatOverflow) {
        this.reformatOverflow = reformatOverflow;
    }
}
