package com.acrylic.main;

import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class GPACalculatorOption extends MenuRedirectOption {

    private final Text[] texts;

    public GPACalculatorOption() {
        super("Cite");
        texts = getDefaultFormatTexts("GPA Calculator", "Calculate your gpa either cumulative or for this term.", "", "Generate reports for this semester or view your progression throughout your school life.");
        init(200, 200, 173, 47, 169, 0.5f);
    }

    @Override
    @NotNull
    public Text[] getTexts() {
        return texts;
    }
}
