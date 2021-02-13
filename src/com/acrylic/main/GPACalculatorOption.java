package com.acrylic.main;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

public class GPACalculatorOption extends MenuRedirectOption {

    private final Text[] texts;

    public GPACalculatorOption() {
        super(new String[] { "gpa", "calculator", "grade", "point", "average" });
        texts = getDefaultFormatTexts("GPA Calculator", "Calculate your gpa either cumulative or for this term.", "", "Generate reports for this semester or view your progression throughout your school life.");
        init(173, 47, 169, 0.5f);
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
