package view;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Theme;

public class Footer extends Component {

	private static int HEIGHT = 8;

	public Footer(Window window) {
		super(window, 0, window.height - HEIGHT, window.width, HEIGHT);
	}

	@Override
	public void update() {
		width = window.width;
		locY = window.height - HEIGHT;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getSeparatorLineColor());
//		g2d.fillRect(locX, locY, width, height);
		g2d.drawLine(locX, locY, width, locY);
	}

}
