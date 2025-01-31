package md2html.markup;

import java.util.List;

public abstract class markupList implements Elements {
    private final List<ListItem> items;
    private final String startBBCode;

    public markupList(List<ListItem> items, String startBBCode) {
        this.items = items;
        this.startBBCode = startBBCode;
    }
    @Override
    public void toHtml(StringBuilder stringBuilder) {
    }
    @Override
    public void toBBCode(StringBuilder stringBuilder){
        stringBuilder.append(startBBCode);
        for(ListItem item: items) {
            item.toBBCode(stringBuilder);
        }
        stringBuilder.append("[/list]");
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {

    }

    @Override
    public int getFromMarkdown(StringBuilder sb, int index) {return 0;}
}
