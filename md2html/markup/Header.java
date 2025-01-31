package md2html.markup;


public class Header implements Elements{
    private Paragraph par;
    private int level;

    public Header(Paragraph par, int level) {
        this.par = par;
        this.level = level;
    }

    public  Header(){};

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<h").append(level).append(">");
        for(AbstractTextElement element: par.elements) {
            element.toHtml(stringBuilder);
        }
        stringBuilder.append("</h").append(level).append(">");
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {

    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {

    }

    @Override
    public int getFromMarkdown(StringBuilder stringBuilder, int index) {
        while(stringBuilder.charAt(index) == '#') {
            index++;
            this.level++;
        }
        index++;
        par = new Paragraph();
        par.getFromMarkdown(stringBuilder, index);
        return index;
    }
}
