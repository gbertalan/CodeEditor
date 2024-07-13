package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utils.Globals;
import utils.Theme;
import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class BoxHeader extends UIComponent {

	private static int TEXT_LEFT_MARGIN = 20;
	private String filename;

	public BoxHeader(Window window, int drawPriority, int locX, int locY, int width, int height, String filename) {
		super(window, drawPriority, locX, locY, width, height);
		// TODO Auto-generated constructor stub
		this.filename = filename;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setFont(Theme.getBoxHeaderFont());

		g2d.setColor(Theme.getBoxHeaderBackgroundColor());
		g2d.fillRect(locX, locY, width, height);

		int textY = Globals.centerTextVert(g2d, filename, Theme.getBoxHeaderFont(), height);

		g2d.setColor(Theme.getBoxHeaderTextColor());
		g2d.drawString(filename, locX + TEXT_LEFT_MARGIN, locY + textY);
		
		g2d.setColor(Theme.getBoxInnerBorderColor());
		g2d.drawRect(locX, locY, width, height);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	

	@Override
	public Cursor getCursor(int secondaryCursor) {
		// TODO Auto-generated method stub
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}
	
	public String getFilename() {
		return filename;
	}

}
