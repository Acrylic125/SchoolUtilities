package com.acrylic.cite;

import com.acrylic.Main;
import com.acrylic.main.MenuRedirectOption;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CiteOption
        extends MenuRedirectOption {

    private final Text[] texts;

    public CiteOption() {
        super(new String[] { "cite", "citation", "quote", "web" });
        texts = getDefaultFormatTexts("Citer", "This useful resource allows you to cite sources with ease!", " ", "Choose Harvard or APA.");
        init(200, 200, 217, 85, 52, 0.5f);
    }

    @Override
    public void handleMouseClicked(@NotNull MouseEvent event) {
        try {
            new CitePage(Main.getProgram().getCurrentSection().getScene()).switchToScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    @NotNull
    public Text[] getTexts() {
        return texts;
    }
}
