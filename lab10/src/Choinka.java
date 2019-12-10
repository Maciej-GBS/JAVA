import javax.swing.*;
import java.awt.*;

public class Choinka implements XmasShape {
	public static void main(String[] args) {
		// write your code here
		JFrame frame = new JFrame("Choinka");
		frame.setContentPane(new DrawPanel());
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	double Sx, Sy;
	int X, Y;

	public Choinka(int x, int y, double scalex, double scaley) {
		this.X = x;
		this.Y = y;
		this.Sx = scalex;
		this.Sy = scaley;
	}

	@Override
	public void transform(Graphics2D g2d) {
		g2d.translate(X, Y);
		g2d.scale(Sx, Sy);
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(126, 60, 26));
		g2d.drawRect(X-20, Y+400, 20, 20);
		for (var i = 4; i >= 1; i--)
		{
			treeLevel(g2d, X, (i-1) * (int)sep + Y, 4);
		}
	}

	void treeLevel(Graphics2D g, int x_off, int y_off, int ratio) {
		ratio = 200 / ratio;
		GradientPaint grad;
		grad = new GradientPaint(x_off, y_off, new Color(0,90,0), x_off, ratio+y_off, new Color(20,200,20));
		g.setPaint(grad);
		int x[] = {0, 200, 0, -200};
		int y[] = {0, ratio, ratio, ratio};
		for (var i = 0; i < x.length; i++)
		{
			x[i] = x[i] + x_off;
			y[i] = y[i] + y_off;
		}
		g.fillPolygon(x, y, x.length);
	}
}
