package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.GeneralPath;

import utils.Theme;
import view.window.Window;

public class CloseButton extends UIComponent {

	private static int WIDTH = 42;
	private static int HEIGHT = 42;
	private static int MARGIN = 16;
	private static Color RED_COLOR = new Color(232, 17, 35);
	private static int ARC_SIZE = 8;

	public CloseButton(Window window, int drawPriority) {
		super(window, drawPriority, window.width - WIDTH, 0, WIDTH, HEIGHT);
	}

	public void draw(Graphics2D g2d) {

		if (hovered) {
			g2d.setColor(RED_COLOR);

			if (window.isMaximized()) {
				g2d.fillRect(locX, locY, WIDTH, HEIGHT);
			} else
				drawRedRectWithOneRoundedCorner(g2d, locX, locY, WIDTH, HEIGHT, ARC_SIZE);
		}

		// Set the color for the 'X'
		if (hovered)
			g2d.setColor(Theme.getFrameButtonSymbolColor());
		else
			g2d.setColor(Color.LIGHT_GRAY);

		// Draw the 'X' in the middle of the button
		g2d.drawLine(locX + MARGIN, locY + MARGIN, locX + width - MARGIN, locY + height - MARGIN);
		g2d.drawLine(locX + MARGIN, locY + height - MARGIN, locX + width - MARGIN, locY + MARGIN);
	}

	@Override
	public void update() {
		locX = window.width - WIDTH;
	}

	private void drawRedRectWithOneRoundedCorner(Graphics2D g2d, int x, int y, int width, int height, int arcSize) {
	    GeneralPath path = new GeneralPath();
	    path.moveTo(x, y);
	    path.lineTo(x + width - arcSize, y);
	    path.quadTo(x + width, y, x + width, y + arcSize);
	    path.lineTo(x + width, y + height);
	    path.lineTo(x, y + height);
	    path.lineTo(x, y);
	    path.closePath();
	    g2d.fill(path);
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}


}
