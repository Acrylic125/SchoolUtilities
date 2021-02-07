package com.acrylic.searcher;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.NotNull;

public interface FXSearchable extends Searchable {

    @NotNull
    Node getNode();

}
