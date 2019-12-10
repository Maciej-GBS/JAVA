import java.awt.*;

public class Bubble implements XmasShape {
	int x;
	int y;
	double scale;
	Color lineColor;
	Color fillColor;

	public Bubble(int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.lineColor = Color.white;
		this.fillColor = Color.white;
	}

	public Bubble(int x, int y, double scale, Color clr) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.lineColor = clr;
		this.fillColor = clr;
	}

	public Bubble(int x, int y, double scale, Color lineColor, Color fillColor) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.lineColor = lineColor;
		this.fillColor = fillColor;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(fillColor);
		g2d.fillOval(0,0,100,100);
		g2d.setColor(lineColor);
		g2d.drawOval(0,0,100,100);
	}

	@Override
	public void transform(Graphics2D g2d) {
		g2d.translate(x,y);
		g2d.scale(scale,scale);
	}
}
