import java.util.List;
import java.io.PrintStream;

public class Paragraph {
	String content;

	Paragraph(String text = "")
	{
		content = text;
	}

	Paragraph setContent(String text)
	{
		content = text;
	}

	void writeHTML(PrintStream out)
	{
		out.printf("<p>%s</p>\n", content);
	}
}
