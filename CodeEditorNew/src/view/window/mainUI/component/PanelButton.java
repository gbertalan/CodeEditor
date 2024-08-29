package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;

import utils.Globals;
import utils.Theme;
import view.window.Window;

public class PanelButton extends UIComponent {

	private static final int TEXT_MARGIN = 5;
	private String buttonText;

	public PanelButton(Window window, int drawPriority, int locX, int locY, int width, int height, String buttonText) {
		super(window, drawPriority, locX, locY, width, height);
		this.buttonText = buttonText;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub

		// background:
		g2d.setColor(Theme.getSidePanelColor());
		g2d.setColor(Color.BLACK);
		g2d.fillRect(locX, locY, width, height);
		
		g2d.setColor(Color.WHITE);
		Globals.drawCenteredText(g2d, locX+TEXT_MARGIN, locY+TEXT_MARGIN+1, width-(TEXT_MARGIN*2), height-(TEXT_MARGIN*2), buttonText);

		// border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(locX, locY, width, height);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}

}
