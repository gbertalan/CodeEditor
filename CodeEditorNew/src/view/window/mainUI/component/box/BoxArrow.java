package view.window.mainUI.component.box;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;

import utils.Theme;
import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class BoxArrow extends UIComponent {

	private static int ARROW_HEAD_WIDTH = 20;
	private static int ARROW_BODY_TOP_MARGIN = 10;
	private static int ARROW_HEAD_TOP_MARGIN = 4;

	public BoxArrow(Window window, int drawPriority, int locX, int locY, int width, int height) {
		super(window, drawPriority, locX, locY, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g2d) {

		// Calculate the points for the triangle
		int[] xPoints = { locX + width - ARROW_HEAD_WIDTH, locX + width, locX + width - ARROW_HEAD_WIDTH };
		int[] yPoints = { locY + ARROW_HEAD_TOP_MARGIN, locY + (height / 2), locY + height - ARROW_HEAD_TOP_MARGIN };

		// Draw the triangle
		g2d.setColor(Theme.getBoxBackgroundColor());
		g2d.fillPolygon(xPoints, yPoints, 3);
		g2d.setColor(Theme.getBoxArrowBorderColor());
		g2d.drawPolygon(xPoints, yPoints, 3);

		// Draw the rectangle border
		g2d.setColor(Theme.getBoxArrowBorderColor());
		g2d.drawRect(locX, locY + ARROW_BODY_TOP_MARGIN, width - ARROW_HEAD_WIDTH,
				height - (ARROW_BODY_TOP_MARGIN * 2));

		// Draw the filled rectangle
		g2d.setColor(Theme.getBoxBackgroundColor());
		g2d.fillRect(locX + 1, locY + ARROW_BODY_TOP_MARGIN + 1, width - ARROW_HEAD_WIDTH + 1,
				height - (ARROW_BODY_TOP_MARGIN * 2) - 2);

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

}
