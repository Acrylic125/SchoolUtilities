package com.acrylic.main;

import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class QuickLinksOption extends MenuRedirectOption {

    private final Text[] texts;

    public QuickLinksOption() {
        super("Cite");
        texts = getDefaultFormatTexts("Quick Link", "A quick access point to store links or files. Organise content based on the subject you are using it for.");
        init(200, 200, 70, 212, 131, 0.5f);
    }

    @Override
    @NotNull
    public Text[] getTexts() {
        return texts;
    }
}
