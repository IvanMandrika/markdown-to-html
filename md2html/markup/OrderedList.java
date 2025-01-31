package md2html.markup;

import java.util.List;

public class OrderedList extends markupList{

    public OrderedList(List<ListItem> items) {
        super(items, "[list=1]");
    }
}
