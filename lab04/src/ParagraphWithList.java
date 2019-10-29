import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
	UnorderedList list = new UnorderedList();

	ParagraphWithList addItem(String item)
	{
		list.addItem(item);
	}

	@Override
	void writeHTML(PrintStream out)
	{
		out.printf("<p>%s</p>\n", content);
	}
}
