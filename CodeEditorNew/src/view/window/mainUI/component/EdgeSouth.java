package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;

import view.window.Window;

public class EdgeSouth extends UIComponent {

	private static int EDGE_THICKNESS = 6;

	public EdgeSouth(Window window, int drawPriority) {
		super(window, drawPriority, 0, window.height - EDGE_THICKNESS, window.width, EDGE_THICKNESS);
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

	public Cursor getCursor(int secondaryCursor) {
		if (window.isMaximized())
			return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		else if (secondaryCursor == Cursor.E_RESIZE_CURSOR) {
			return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
		} else if (secondaryCursor == Cursor.W_RESIZE_CURSOR) {
			return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
		} else {
			return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
		}
	}

}
