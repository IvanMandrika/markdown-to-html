package md2html.markup;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractTextSelection extends AbstractTextElement {
    private List<AbstractTextElement> elements = new LinkedList<>();
    private String selection;
    private String selectionBB;

    private String selectionHtml;

    public AbstractTextSelection(List<AbstractTextElement> elements, String selection, String selectionBB, String selectionHtml) {
        this.elements = elements;
        this.selection = selection;
        this.selectionBB = selectionBB;
        this.selectionHtml = selectionHtml;

    }

    public AbstractTextSelection(String selection, String selectionBB, String selectionHtml){
        this.selection = selection;
        this.selectionBB = selectionBB;
        this.selectionHtml = selectionHtml;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(selection);
        for(AbstractTextElement element: elements) {
            element.toMarkdown(stringBuilder);
        }
        stringBuilder.append(selection);
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        stringBuilder.append("[").append(selectionBB).append("]");
        for(AbstractTextElement element: elements) {
            element.toBBCode(stringBuilder);
        }
        stringBuilder.append("[/").append(selectionBB).append("]");
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<").append(selectionHtml).append(">");
        for(AbstractTextElement element: elements) {
            element.toHtml(stringBuilder);
        }
        stringBuilder.append("</").append(selectionHtml).append(">");
    }

    @Override
    public int getFromMarkdown(StringBuilder stringBuilder, int index) {
        selection = stringBuilder.substring(index, index + selection.length());
        index += selection.length();
        int flag = 0;
        while(index < stringBuilder.length()) {
            while (index < stringBuilder.length() && stringBuilder.charAt(index) == selection.charAt(flag)) {
                flag++;
                index++;

                if (flag == selection.length()) {
                    if (selection.length() == 1
                            && index < stringBuilder.length()
                            && stringBuilder.charAt(index) == selection.charAt(0)) {
                        break;
                    } else return index;
                }

            } if (flag != 0) {
                index -= flag;
                flag = 0;
            }
            AbstractTextElement element = new Text();
            if (index + 2 < stringBuilder.length()) {

                if ((stringBuilder.charAt(index) == '*'
                        || stringBuilder.charAt(index) == '_')
                        && stringBuilder.charAt(index + 1) != ' ' && stringBuilder.charAt(index + 1) != System.lineSeparator().charAt(0)) {
                    if (stringBuilder.charAt(index + 1) == stringBuilder.charAt(index)) {
                        element = new Strong();
                    } else {
                        element = new Emphasis();
                    }

                } else if (stringBuilder.charAt(index) == '-'
                        && stringBuilder.charAt(index + 1) == '-') {
                    element = new Strikeout();
                } else if ("```".equals(stringBuilder.substring(index, index + 3))) {
                    element = new PreCode();
                } else if (stringBuilder.charAt(index) == '`' ) {
                    element = new Code();
                }

            }
            index = element.getFromMarkdown(stringBuilder, index);
            elements.add(element);

        }
        return index;

    }
}
