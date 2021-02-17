package com.acrylic.cite;

import com.acrylic.Main;
import com.acrylic.cache.SectionMap;
import com.acrylic.fxobjects.RedirectOption;
import com.acrylic.main.MenuRedirectOption;
import com.acrylic.sections.AbstractSection;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CiteOption
        extends MenuRedirectOption {

    private final Text[] texts;

    public CiteOption() {
        super(new String[] { "cite", "citation", "quote", "web" });
        texts = RedirectOption.getDefaultFormatTexts("Citer", "This useful resource allows you to cite sources with ease!", " ", "Choose Harvard or APA.");
        init(217, 85, 52, 0.5f);
    }

    @Override
    public void handleMouseClicked(@NotNull MouseEvent event) {
        try {
            SectionMap<AbstractSection> sectionMap = Main.getProgram().getSectionMap();;
            AbstractSection section = sectionMap.getSection(CiteSection.class);
            if (section == null)
                section = new CiteSection();
            sectionMap.cache(section);
            section.switchToScene();
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
