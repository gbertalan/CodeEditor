package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import utils.Theme;
import view.window.Window;

public class Background extends UIComponent {

	private static int ARC_AMOUNT = 18;
	private static int ARC_SIZE = 8;
	private static int TITLEBAR_HEIGHT = 42;

	public Background(Window window, int drawPriority) {
		super(window, drawPriority, 0, 0, window.width, window.height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBackgroundColor());

		if (window.isMaximized()) {
			g2d.fillRect(0, 0, window.width, window.height);
			g2d.setColor(Theme.getSeparatorLineColor());
			g2d.drawRect(0, 0, window.width-1, window.height);
		} else {
			drawRectWithTwoBottomRoundedCorners(g2d, 0, TITLEBAR_HEIGHT, window.width, window.height - TITLEBAR_HEIGHT,
					ARC_SIZE, true);
			g2d.setColor(Theme.getSeparatorLineColor());
			drawRectWithTwoBottomRoundedCorners(g2d, 0, TITLEBAR_HEIGHT, window.width-1, window.height - TITLEBAR_HEIGHT,
					ARC_SIZE, false);
		}
	}

	private void drawRectWithTwoBottomRoundedCorners(Graphics2D g2d, int x, int y, int width, int height, int arcSize,
			boolean fill) {
		GeneralPath path = new GeneralPath();
		path.moveTo(x, y);
		path.lineTo(x + width, y);
		path.lineTo(x + width, y + height - arcSize);
		path.quadTo(x + width, y + height, x + width - arcSize, y + height);
		path.lineTo(x + arcSize, y + height);
		path.quadTo(x, y + height, x, y + height - arcSize);
		path.lineTo(x, y);
		path.closePath();
		if (fill)
			g2d.fill(path);
		else
			g2d.draw(path);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		width = window.width;
		height = window.height;
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}

}
