package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;

import utils.Theme;
import view.window.Window;

public class EditorPanel extends UIComponent {

	private static int TOP_MARGIN = 0;
	private static int RIGHT_MARGIN = 8;
	private static int WIDTH = 720;
	private static int BOTTOM_MARGIN = 9;

	public EditorPanel(Window window, int drawPriority) {
		super(window, drawPriority, window.width - WIDTH - RIGHT_MARGIN, TOP_MARGIN, WIDTH,
				window.height - TOP_MARGIN - BOTTOM_MARGIN);
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
		locX = window.width - WIDTH - RIGHT_MARGIN;
		height = window.height - TOP_MARGIN - BOTTOM_MARGIN;
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		// TODO Auto-generated method stub
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}

}
