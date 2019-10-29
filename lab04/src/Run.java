import java.io.PrintStream;

public class Run {
	public static void main(String args[])
	{
		Document cv = new Document("Jana Kowalski - CV");
		cv.setPhoto("...");
		cv.addSection("Wykształcenie")
			.addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
			.addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
			.addParagraph("...");
		cv.addSection("Umiejętności");
		/*
			.addParagraph(new ParagraphWithList()
					.setContent("Umiejętności")
					.addListItem("C")
					.addListItem("C++")
					.addListItem("Java"));
					*/
		cv.writeHTML(System.out);
		//cv.writeHTML(new PrintStream("cv.html", "UTF-8"));
	}
}
