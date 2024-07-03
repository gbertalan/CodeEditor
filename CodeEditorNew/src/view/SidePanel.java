package view;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Theme;

public class SidePanel extends Component {

	private static int TOP_MARGIN = 42;
	private static int WIDTH = 55;
	private static int BOTTOM_MARGIN = 9;

	public SidePanel(Window window) {
		super(window, 1, TOP_MARGIN + 1, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN);
	}

	@Override
	public void update() {
		height = window.height - TOP_MARGIN - BOTTOM_MARGIN;

	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getSidePanelColor());
		g2d.fillRect(locX, locY, width, height);
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(width, locY, width, locY + height);
	}

}
