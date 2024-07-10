package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import utils.Theme;
import view.window.Window;

public class TitleBar extends UIComponent {

	private static int TITLEBAR_HEIGHT = 42;
	private static int ARC_SIZE = 8;

	public TitleBar(Window window, int drawPriority) {
		super(window, drawPriority, 0, 0, window.width, TITLEBAR_HEIGHT);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getTitleBarColor());
		drawRectWithTwoRoundedCorners(g2d, locX, locY, width, height, ARC_SIZE, true);

		g2d.setColor(Theme.getSeparatorLineColor());
		drawRectWithTwoRoundedCorners(g2d, locX, locY, width-1, height, ARC_SIZE, false);
	}

	@Override
	public void update() {
		width = window.width;
	}

	private void drawRectWithTwoRoundedCorners(Graphics2D g2d, int x, int y, int width, int height, int arcSize,
			boolean fill) {
		GeneralPath path = new GeneralPath();
		path.moveTo(x + arcSize, y);
		path.lineTo(x + width - arcSize, y);
		path.quadTo(x + width, y, x + width, y + arcSize);
		path.lineTo(x + width, y + height);
		path.lineTo(x, y + height);
		path.lineTo(x, y + arcSize);
		path.quadTo(x, y, x + arcSize, y);
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

//	@Override
//	public boolean isInRegion(MouseEvent e) {
//		Rectangle rectangle = new Rectangle(locX, locY, width-(3*CONTROL_BUTTON_WIDTH), height);
//		return rectangle.contains(e.getPoint()) ? true : false;
//	}
}
