/**
 * I don't use this file anymore, I just leave it here for a while,
 * because there are valuable solutions in it.
 */

package model;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JPanel;

import utils.Theme;
import view.window.Listener;
import view.window.Window;

public class FileBox {

	private String fileName;
	private int x;
	private int y;
	private int width = 300;
	private int height = 400;
	private int clickedX;
	private int clickedY;

	private boolean active;
	private boolean grabbed;
	private boolean justCreated = true; // when it is created, its location is not set yet so it is drawn to the wrong
										// location. to avoid this, we only draw it when it is not justCreated anymore.
	private boolean shouldBeDrawn = true;

	private static int HEADER_HEIGHT = 30;
	private static final int TEXT_LEFT_MARGIN = 30;
	private static final int TEXT_TOP_MARGIN = 21;
	private static Color BACKGROUND_COLOR = Theme.getBackgroundColor();

	public FileBox(Window window, String fileName, int clickedX, int clickedY, boolean grabbed) {
		this.fileName = fileName;
		this.clickedX = clickedX;
		this.clickedY = clickedY;
		this.grabbed = grabbed;
		this.active = true;

	}

	public void draw(Graphics2D g2d) {

		if (justCreated) {
			justCreated = false;
		} else {

			if (grabbed) {
//				x = Listener.mouseDragged.x - clickedX;
//				y = Listener.mouseDragged.y - clickedY;
			}

			// background:
			g2d.setColor(BACKGROUND_COLOR);
			g2d.fillRect(x, y, width, height);

			// header:
			g2d.setColor(Theme.getPanelButtonHoverColor());

			g2d.fillRect(x, y, width, HEADER_HEIGHT);

			g2d.setFont(new Font("Verdana", Font.PLAIN, 14));
			// filename:
			g2d.setColor(Theme.getPanelTextColor());
			g2d.drawString(fileName, x + TEXT_LEFT_MARGIN, y + TEXT_TOP_MARGIN);

			// border:
			if (grabbed)
				g2d.setColor(Color.WHITE);
			else
				g2d.setColor(Theme.getSeparatorLineColor());

			g2d.drawRect(x, y, width, height);

		}
	}

	public boolean isGrabbed() {
		return grabbed;
	}

	public void setGrabbed(boolean grabbed) {
		this.grabbed = grabbed;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isJustCreated() {
		return justCreated;
	}

	public void setJustCreated(boolean justCreated) {
		this.justCreated = justCreated;
	}

	public void setShouldBeDrawn(boolean shouldBeDrawn) {
		this.shouldBeDrawn = shouldBeDrawn;
	}
}
