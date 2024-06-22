package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.GeneralPath;

import utils.Theme;

public class CloseButton extends Component {

	private static int WIDTH = 42;
	private static int HEIGHT = 42;
	private static int MARGIN = 16;
	private static Color RED_COLOR = new Color(232, 17, 35);
	private static int ARC_SIZE = 8;

	public CloseButton(Window window) {
		super(window, window.width - WIDTH, 0, WIDTH, HEIGHT);
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

	public void drawRedRectWithOneRoundedCorner(Graphics2D g2d, int x, int y, int width, int height, int arcSize) {

		// Create a GeneralPath object
		GeneralPath path = new GeneralPath();

		// Move to the starting point (top-left corner)
		path.moveTo(x, y);

		// Draw the top edge to the top-right corner
		path.lineTo(x + width - arcSize, y);

		// Draw the arc for the rounded top-right corner
		path.quadTo(x + width, y, x + width, y + arcSize);

		// Draw the right edge
		path.lineTo(x + width, y + height);

		// Draw the bottom edge
		path.lineTo(x, y + height);

		// Draw the left edge
		path.lineTo(x, y);

		// Close the path (back to the starting point)
		path.closePath();

		// Draw the path
		g2d.fill(path);
	}

}
