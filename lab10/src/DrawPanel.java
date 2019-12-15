import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
	public java.util.List<XmasShape> shapes = new ArrayList<>();

	public DrawPanel() {
		setBackground(new Color(255,80,60));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		System.out.println("> Painting...");

		g.setColor(Color.green);
		g.setFont(new Font("Helvetica", Font.ITALIC, 18));
		g.drawString("Ho Ho Choinka", 420, 20);

		for (var s : shapes)
			s.draw(g2d);
	}
}
