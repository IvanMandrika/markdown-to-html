package md2html.markup;

import java.util.List;

public class Strikeout extends AbstractTextSelection {
    public Strikeout (List<AbstractTextElement> elements) {
        super(elements, "~", "s", "s");
    }

    public Strikeout() {super("--", "s", "s");}
}
