package view.canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import utils.Theme;
import view.Window;

public class MaxButton extends Component {

	private static int WIDTH = 42;
	private static int HEIGHT = 42;
	private static int MARGIN = 16;

	public MaxButton(Window window) {
		super(window, window.width - (WIDTH * 2), 0, WIDTH, HEIGHT);
	}

	public void draw(Graphics2D g2d) {

		if (hovered) {
			g2d.setColor(Theme.getFrameButtonColor());
			g2d.fillRect(locX, locY, WIDTH, HEIGHT);
		}

		// Set the color for the 'X'
		if (hovered)
			g2d.setColor(Color.WHITE);
		else
			g2d.setColor(Color.LIGHT_GRAY);

		// Draw the square '□' in the middle of the button
//		g2d.drawRect(locX + MARGIN, locY + MARGIN, width - 2 * MARGIN, height - 2 * MARGIN);

		if (window.isMaximized()) {
			// Draw two squares, one behind the other
			g2d.drawRect(locX + MARGIN + 1, locY + MARGIN + 1, width - 2 * MARGIN - 1, height - 2 * MARGIN - 1);

			g2d.drawLine(locX + MARGIN + 4, locY + MARGIN - 1, locX + MARGIN + 12, locY + MARGIN - 1);
			g2d.drawLine(locX + MARGIN + 12, locY + MARGIN - 0, locX + MARGIN + 12, locY + MARGIN + 8);
		} else {
			// Draw a single square '□' in the middle of the button
			g2d.drawRect(locX + MARGIN, locY + MARGIN, width - 2 * MARGIN, height - 2 * MARGIN);
		}
	}

	@Override
	public void update() {
		locX = window.width - (WIDTH * 2);
	}

}
