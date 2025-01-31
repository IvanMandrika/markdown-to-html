package md2html.markup;

public class Text extends AbstractTextElement {
    private String string;
    public Text(String string) {
        this.string = string;
    }

    public Text() {}

    private String replaceAbleSymbol(char ch) {
        if (ch == '&') return "&amp;";
        if (ch == '<') return "&lt;";
        if (ch == '>') return "&gt;";
        return Character.toString(ch);
    }


    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(string);
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        stringBuilder.append(string);
    }

    @Override
    public int getFromMarkdown(StringBuilder stringBuilder, int index) {
        StringBuilder text = new StringBuilder();
        if (stringBuilder.charAt(index) == '\\') {
            text.append(stringBuilder.charAt(index+1));
            index+=2;
        } else {
            text.append(replaceAbleSymbol(stringBuilder.charAt(index)));
            index++;
        }
        while (index < stringBuilder.length() && !(stringBuilder.charAt(index) == '*'
                || stringBuilder.charAt(index) == '-'
                || stringBuilder.charAt(index) == '_'
                || stringBuilder.charAt(index) == '`')) {
            if (stringBuilder.charAt(index) == '\\') {
                text.append(stringBuilder.charAt(index+1));
                index+=2;
            } else {
                text.append(replaceAbleSymbol(stringBuilder.charAt(index)));
                index++;
            }
        }
//        if (stringBuilder.length() - index == 0) index++;
        string = text.toString();
        return index;
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(string);
    }

}
