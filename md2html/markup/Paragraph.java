package md2html.markup;

import java.util.LinkedList;
import java.util.List;

public class Paragraph implements Elements{
    List<AbstractTextElement> elements = new LinkedList<>();
    public Paragraph(List<AbstractTextElement> elements) {
        this.elements = elements;
    }
    public Paragraph(){}

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        for(AbstractTextElement element: elements) {
            element.toMarkdown(stringBuilder);
        }
    }
    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<p>");
        for(AbstractTextElement element: elements) {
            element.toHtml(stringBuilder);
        }
        stringBuilder.append("</p>");

    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        for(AbstractTextElement element: elements) {
            element.toBBCode(stringBuilder);
        }
    }

    @Override
    public int getFromMarkdown(StringBuilder stringBuilder, int index) {
        while(index < stringBuilder.length()) {
            AbstractTextElement element = new Text();
            if (index + 2 < stringBuilder.length()) {

                if ((stringBuilder.charAt(index) == '*'
                        || stringBuilder.charAt(index) == '_')
                        && stringBuilder.charAt(index + 1) != ' ' && stringBuilder.charAt(index + 1) != System.lineSeparator().charAt(0)) {

                    if (stringBuilder.charAt(index + 1) == stringBuilder.charAt(index)) {
                        element = new Strong();
                    } else element = new Emphasis();

                } else if (stringBuilder.charAt(index) == '-'
                        && stringBuilder.charAt(index + 1) == '-') {
                    element = new Strikeout();
                } else if ("```".equals(stringBuilder.substring(index, index + 3))) {
                    element = new PreCode();
                } else if (stringBuilder.charAt(index) == '`' ) {
                    element = new Code();
                }

            } else element = new Text();
            index  = element.getFromMarkdown(stringBuilder, index);
            elements.add(element);
        }
        return index;


    }
}
