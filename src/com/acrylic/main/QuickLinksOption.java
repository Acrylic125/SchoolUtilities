package com.acrylic.main;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class QuickLinksOption extends MenuRedirectOption {

    private final Text[] texts;

    public QuickLinksOption() {
        super(new String[] { "quick", "links", "direct", "redirect" });
        texts = getDefaultFormatTexts("Quick Link", "A quick access point to store links or files. Organise content based on the subject you are using it for.");
        init(200, 200, 70, 212, 131, 0.5f);
    }

    @Override
    public void handleMouseClicked(@NotNull MouseEvent event) {

    }

    @Override
    @NotNull
    public Text[] getTexts() {
        return texts;
    }
}
