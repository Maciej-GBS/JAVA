import java.util.List;
import java.io.PrintStream;

public class Section {
	String title;
	List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	Section(String title = "")
	{
		this.title = title;
	}

	Section setTitle(String title)
	{
		this.title = title;
	}

	Section addParagraph(String text)
	{
		return addParagraph(new Paragraph(text));
	}

	Section addParagraph(Paragraph p)
	{
		paragraphs.add(p);
		return this;
	}

	void writeHTML(PrintStream out)
	{
		out.printf("<h2>%s</h2>\n", title);
		var iter = paragraphs.iterator();
		while (iter.hasNext())
			iter.next().writeHTML(out);
	}
}

