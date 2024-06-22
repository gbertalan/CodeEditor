package visual_components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import main.Window;
import utils.Theme;

public class CloseButton extends Component {

	private static int SIZE = 42;
	private static int ARC_AMOUNT = 18;
	private Image image;
	private static int X_MARGIN = 16;

	public CloseButton(Window window) {
		super(window, window.width - SIZE, 0, SIZE, SIZE);
		image = Toolkit.getDefaultToolkit().getImage("resources/close_button.png");

	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getDEBUGColor());
		g2d.fillRoundRect(locX, locY, width, height, ARC_AMOUNT, ARC_AMOUNT);
		while (!g2d.drawImage(image, locX, locY, null)) {
			// spin while loading image
		}

		// Set the color for the 'X'
		if (hovered)
			g2d.setColor(Color.WHITE);
		else
			g2d.setColor(Color.RED);

		// Draw the 'X' in the middle of the button
		g2d.drawLine(locX + X_MARGIN, locY + X_MARGIN, locX + width - X_MARGIN, locY + height - X_MARGIN);
		g2d.drawLine(locX + X_MARGIN, locY + height - X_MARGIN, locX + width - X_MARGIN, locY + X_MARGIN);
	}

	@Override
	public void update() {
		locX = window.width - SIZE;
	}

}
