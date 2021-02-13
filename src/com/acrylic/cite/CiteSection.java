package com.acrylic.cite;

import com.acrylic.Main;
import com.acrylic.base.BaseController;
import com.acrylic.paneaggregators.CollectionGridAggregator;
import com.acrylic.sections.AbstractSection;
import com.acrylic.utils.SceneBuilder;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public final class CiteSection extends AbstractSection {

    private final GridPane gridPane = new GridPane();

    public CiteSection() throws IOException {
        super(new SceneBuilder(Main.class, "base/basecontroller.fxml"));
    }

    @Override
    public void initialize() {
        GridPane.setHalignment(gridPane, HPos.CENTER);
        gridPane.add();
    }
}
