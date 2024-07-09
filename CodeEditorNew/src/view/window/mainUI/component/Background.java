package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;

import utils.Theme;
import view.window.Window;

public class Background extends UIComponent{
	
	private static int ARC_AMOUNT = 18;

	public Background(Window window, int drawPriority) {
		super(window, drawPriority, 0, 0, window.width, window.height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBackgroundColor());
		g2d.setColor(new Color(130, 30, 30));
		g2d.setColor(new Color(0, 0, 255, 55));

		if (window.isMaximized()) {
			g2d.fillRect(0, 0, window.width, window.height);
			g2d.setColor(Theme.getSeparatorLineColor());
			g2d.drawRect(0, 0, window.width, window.height);
		} else {
			g2d.fillRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
			g2d.setColor(Theme.getSeparatorLineColor());
			g2d.drawRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		width = window.width;
		height = window.height;
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}

}
