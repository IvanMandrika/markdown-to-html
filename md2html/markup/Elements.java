package md2html.markup;

public interface Elements {
    void toMarkdown(StringBuilder stringBuilder);
    void toHtml(StringBuilder stringBuilder);
    void toBBCode(StringBuilder stringBuilder);
    int getFromMarkdown(StringBuilder stringBuilder, int index);
}
