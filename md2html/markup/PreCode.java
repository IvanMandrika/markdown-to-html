package md2html.markup;

public class PreCode extends AbstractTextElement{
    private String string;
    public PreCode(String string) {
        this.string = string;
    }

    public PreCode() {}

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {

    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<pre>");
        stringBuilder.append(string);
        stringBuilder.append("</pre>");
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {

    }

    @Override
    public int getFromMarkdown(StringBuilder stringBuilder, int index) {
        index+=3;
        StringBuilder text = new StringBuilder();
        int flag = 0;
        while (flag != 3) {
            if (stringBuilder.charAt(index) == '`') flag += 1;
            else if (stringBuilder.charAt(index) != '`' && flag != 0) flag = 0;
            text.append(stringBuilder.charAt(index));
            index++;
        }
//        if (stringBuilder.length() - index == 0) index++;
        string = text.substring(0, text.length()-3);
//        index ++;
        return index;
    }
}
