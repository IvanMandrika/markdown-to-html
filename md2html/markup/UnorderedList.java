package md2html.markup;

import java.util.List;

public class UnorderedList extends markupList{

    public UnorderedList(List<ListItem> items) {
        super(items, "[list]");
    }
}
