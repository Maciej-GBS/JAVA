import java.awt.*;

public class Snowflake implements XmasShape {
	int x;
	int y;
	double scale;
	Color fillColor;

	public Snowflake(int x, int y, double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.fillColor = Color.white;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(fillColor);
		g2d.drawString("*", 0, 0);
	}

	@Override
	public void transform(Graphics2D g2d) {
		g2d.translate(x,y);
		g2d.scale(scale,scale);
	}
}
