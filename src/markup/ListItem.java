package md2html.markup;

import java.util.List;

public class ListItem implements Elements {
    private final List<?extends Elements> elements;

    public ListItem (List<?extends Elements> elements) {this.elements = elements;
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {

    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        stringBuilder.append("[*]");
        for (Elements element: elements) {
            element.toBBCode(stringBuilder);
        }
    }

    @Override
    public int getFromMarkdown(StringBuilder stringBuilder, int index) {
        return 0;
    }
}
