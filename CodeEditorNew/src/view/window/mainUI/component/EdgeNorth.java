package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;

import view.window.Window;

public class EdgeNorth extends UIComponent {

	private static int EDGE_THICKNESS = 6;

	public EdgeNorth(Window window, int drawPriority) {
		super(window, drawPriority, 0, 0, window.width, EDGE_THICKNESS);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// draw nothing
	}

	@Override
	public void update() {
		width = window.width;
	}

	public Cursor getCursor(int secondaryCursor) {
		if (window.isMaximized())
			return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		else if (secondaryCursor == Cursor.E_RESIZE_CURSOR) {
			return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
		} else if (secondaryCursor == Cursor.W_RESIZE_CURSOR) {
			return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
		} else {
			return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
		}
	}

}
