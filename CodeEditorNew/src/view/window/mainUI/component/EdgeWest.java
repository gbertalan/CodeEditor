package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;

import view.window.Window;

public class EdgeWest extends UIComponent {

	private static int EDGE_THICKNESS = 6;

	public EdgeWest(Window window) {
		super(window, 0, 0, EDGE_THICKNESS, window.height);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// draw nothing
	}

	@Override
	public void update() {
		height = window.height;
	}

	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
	}
}
