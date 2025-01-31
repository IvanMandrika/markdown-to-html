package md2html.markup;
import java.util.List;

public class Strong extends AbstractTextSelection {
    public Strong (List<AbstractTextElement> elements) {
        super(elements, "__", "b", "strong");
    }
    public Strong(){super("__", "b", "strong");}
}
