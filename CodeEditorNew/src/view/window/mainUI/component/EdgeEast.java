package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;

import view.window.Window;

public class EdgeEast extends UIComponent {

	private static int EDGE_THICKNESS = 6;

	public EdgeEast(Window window) {
		super(window, window.width - EDGE_THICKNESS, 0, EDGE_THICKNESS, window.height);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// draw nothing
	}

	@Override
	public void update() {
		locX = window.width - EDGE_THICKNESS;
		height = window.height;
	}

	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
	}
}
