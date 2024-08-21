package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;

import utils.Theme;
import view.window.Window;

public class FileButton extends UIComponent {

	private static int TOP_MARGIN = 42;
	private static int WIDTH = 8;
	private static int HEIGHT = 55;

	public FileButton(Window window, int drawPriority) {
		super(window, drawPriority, 0, TOP_MARGIN, WIDTH, HEIGHT);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.setColor(Theme.getPanelButtonColor());
		if (hovered) {
			g2d.setColor(Theme.getPanelButtonHoverColor());
			g2d.setColor(Color.YELLOW);
		} else {
			if (true) {// csak kitolto

			} else {
				g2d.setColor(Theme.getPanelButtonHoverColor());
			}
		}

		g2d.fillRect(locX, locY, width, height);

		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(locX, locY, width, height);
	}

	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}
}
