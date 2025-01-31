package md2html.markup;

import java.util.List;

public class Emphasis extends AbstractTextSelection {
    public Emphasis (List<AbstractTextElement> elements) {
        super(elements, "*", "i", "em");
    }
    public Emphasis(){super("*", "i", "em");};
}
