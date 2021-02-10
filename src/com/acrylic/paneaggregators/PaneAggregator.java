package com.acrylic.paneaggregators;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface PaneAggregator {

    @NotNull Pane getPane();

    @NotNull Collection<Node> getNodes();

}
