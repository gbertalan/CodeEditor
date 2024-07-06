package view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JPanel;

import control.WindowListener;

public class FileBox {

	private String fileName;
	private int x;
	private int y;
	private int width = 300;
	private int height = 400;
	private int clickedX;
	private int clickedY;

	private boolean grabbed;
	private boolean justCreated = true;

	private static int HEADER_HEIGHT = 30;

	public FileBox(Window window, String fileName, int clickedX, int clickedY, boolean grabbed) {
		this.fileName = fileName;
		this.clickedX = clickedX;
		this.clickedY = clickedY;
		this.grabbed = grabbed;

	}

	public void draw(Graphics2D g2d) {

		if (justCreated) {
			justCreated = false;
		} else {
			if (grabbed) {
				x = WindowListener.mouseDragged.x - clickedX;
				y = WindowListener.mouseDragged.y - clickedY;
			}

			// background:
			g2d.setColor(Color.CYAN);
			g2d.fillRect(x, y, width, height);

			// header:
			g2d.setColor(Color.GREEN);
			g2d.fillRect(x, y, width, HEADER_HEIGHT);

			// border:
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x, y, width, height);
		}
	}

	public boolean isGrabbed() {
		return grabbed;
	}

	public void setGrabbed(boolean grabbed) {
		this.grabbed = grabbed;
	}
}
