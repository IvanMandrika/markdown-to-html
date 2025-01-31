package md2html.markup;
import java.util.List;

public class Code extends AbstractTextSelection {
    public Code (List<AbstractTextElement> elements) {
        super(elements, "`", "", "code");
    }

    public Code() {super("`", "", "code");}
}
