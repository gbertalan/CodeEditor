package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;

import utils.Theme;
import view.window.Window;

public class ConsolePanel extends UIComponent {

	private static final int LOC_X = 228;
	private static final int HEIGHT = 220;

	public ConsolePanel(Window window, int drawPriority) {
		super(window, drawPriority, LOC_X, window.height - HEIGHT - 8, window.width - LOC_X - 8, HEIGHT);

//		locY = window.height - HEIGHT - 8;
//		width = window.width - LOC_X;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		// background:
		g2d.setColor(Theme.getSidePanelColor());
		g2d.fillRect(locX, locY, width, height);

		// border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(locX, locY, width, height);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		locY = window.height - HEIGHT - 8;
		width = window.width - LOC_X - 8;
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}

}
