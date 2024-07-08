package view.window.mainUI.component;

import java.awt.Graphics2D;

import view.window.Window;

public class EdgeSouth extends UIComponent {

	private static int EDGE_THICKNESS = 6;

	public EdgeSouth(Window window) {
		super(window, 0, window.height - EDGE_THICKNESS, window.width, EDGE_THICKNESS);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// draw nothing
	}

	@Override
	public void update() {
		locY = window.height - EDGE_THICKNESS;
		width = window.width;
	}

}
