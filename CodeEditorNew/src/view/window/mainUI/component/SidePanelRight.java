package view.window.mainUI.component;

import java.awt.Graphics2D;

import utils.Theme;
import view.window.Window;

public class SidePanelRight extends UIComponent {
	
	private static int TOP_MARGIN = 42;
	private static int WIDTH = 8;
	private static int BOTTOM_MARGIN = 9;

	public SidePanelRight(Window window) {
		super(window, window.width-WIDTH-1, TOP_MARGIN, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN);
	}

	@Override
	public void update() {
		height = window.height - TOP_MARGIN - BOTTOM_MARGIN;
		locX = window.width-WIDTH-1;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getSidePanelColor());
		g2d.fillRect(locX, locY, width, height);
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(locX, locY, locX, locY + height);
	}

}
