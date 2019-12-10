import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
	public DrawPanel() {
		setBackground(new Color(255,80,60));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		System.out.println("> Painting...");

		g.setColor(Color.green);
		g.setFont(new Font("Helvetica", Font.ITALIC, 18));
		g.drawString("Ho Ho Ho Choinka", 400, 20);

		XmasShape tree = new Choinka(500, 100, 0.5, 1.5);
		tree.draw(g2d);
	}
}
