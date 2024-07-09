package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import utils.Theme;
import view.window.Window;

public class TrayButton extends UIComponent {

	private static int WIDTH = 42;
	private static int HEIGHT = 42;
	private static int MARGIN = 16;

	public TrayButton(Window window, int drawPriority) {
		super(window, drawPriority, window.width - (WIDTH * 3), 0, WIDTH, HEIGHT);
	}

	public void draw(Graphics2D g2d) {

		if (hovered) {
			g2d.setColor(Theme.getFrameButtonColor());
			g2d.fillRect(locX, locY, WIDTH, HEIGHT);
		}

		// Set the color for the 'X'
		if (hovered)
			g2d.setColor(Color.WHITE);
		else
			g2d.setColor(Color.LIGHT_GRAY);

		// Draw the '_' in the middle of the button
		g2d.drawLine(locX + MARGIN, locY + height - MARGIN - 5, locX + width - MARGIN, locY + height - MARGIN - 5);
	}

	@Override
	public void update() {
		locX = window.width - (WIDTH * 3);
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}

}
