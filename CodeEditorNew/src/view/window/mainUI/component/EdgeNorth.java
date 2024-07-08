package view.window.mainUI.component;

import java.awt.Graphics2D;

import view.window.Window;

public class EdgeNorth extends UIComponent {

	private static int EDGE_THICKNESS = 6;

	public EdgeNorth(Window window) {
		super(window, 0, EDGE_THICKNESS, window.width, EDGE_THICKNESS);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// draw nothing
	}

	@Override
	public void update() {
		width = window.width;
	}

}
