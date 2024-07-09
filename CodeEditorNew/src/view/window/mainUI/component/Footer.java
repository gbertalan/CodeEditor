package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import utils.Theme;
import view.window.Window;

public class Footer extends UIComponent {

	private static int HEIGHT = 8;
	private static int ARC_SIZE = 8;

	public Footer(Window window, int drawPriority) {
		super(window, drawPriority, 0, window.height - HEIGHT, window.width, HEIGHT);
	}

	@Override
	public void update() {
		width = window.width;
		locY = window.height - HEIGHT;
	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.setColor(Theme.getSidePanelColor());
		drawRectWithTwoRoundedCorners(g2d, locX, locY, width, height, ARC_SIZE, true);

		g2d.setColor(Theme.getSeparatorLineColor());
//		g2d.fillRect(locX, locY, width, height);
		g2d.drawLine(locX, locY, width, locY);
	}

	private void drawRectWithTwoRoundedCorners(Graphics2D g2d, int x, int y, int width, int height, int arcRadius,
			boolean fill) {
		GeneralPath path = new GeneralPath();

		path.moveTo(x, y);
		path.lineTo(x + width, y);
		path.lineTo(x + width, y + height - arcRadius);
		path.quadTo(x + width, y + height, x + width - arcRadius, y + height);
		path.lineTo(x + arcRadius, y + height);
		path.quadTo(x, y + height, x, y + height - arcRadius);
		path.lineTo(x, y);
		path.closePath();
		if (fill)
			g2d.fill(path);
		else
			g2d.draw(path);
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}
}
