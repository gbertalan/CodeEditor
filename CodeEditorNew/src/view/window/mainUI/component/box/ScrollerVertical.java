package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Theme;

public class ScrollerVertical {

	private int bigWidth;
	private int bigHeight;
	private int smallHeight = 50; // Default to 50% of the big rectangle's height
	private int smallY = 0; // Initial position of the small rectangle
	private Color bigColor = Theme.getScrollBarColorBig();
	private Color smallColor = Theme.getScrollBarColorSmall();

	private BoxContent boxContent;

	private int noOfFileLines = 50;
	private int noOfDisplayedLines = 32;

	public ScrollerVertical(BoxContent boxContent) {
		this.boxContent = boxContent;

	}

	public void draw(Graphics2D g2d) {

		// Draw the big rectangle
		this.bigWidth = (boxContent.getBox().getHeight() / 16) - 2;
		this.bigHeight = boxContent.getHeight();
		g2d.setColor(bigColor);
		int bigLocX = boxContent.getLocX() + boxContent.getWidth() - bigWidth;
		int bigLocY = boxContent.getLocY();
		g2d.fillRect(bigLocX, bigLocY, bigWidth, bigHeight);

		// Draw the small rectangle

		calculateSmallHeight();
		System.out.println();
//		System.out.println("lineRatio: " + lineRatio);

//		smallHeight = (int) Math.round(boxContent.getHeight() * lineRatio);
		g2d.setColor(smallColor);
		g2d.fillRect(bigLocX, bigLocY + smallY, bigWidth, smallHeight);

		// left border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(bigLocX, bigLocY, bigLocX, bigLocY + boxContent.getHeight());
	}

	public void calculateSmallHeight() {
		double lineRatio;
		if (boxContent.getNoOfAllLines() < boxContent.getNoOfDisplayedLines()) {
			lineRatio = 1.0;
		}
		lineRatio = (double) (boxContent.getNoOfDisplayedLines()) / boxContent.getNoOfAllLines();

		smallHeight = (int) Math.round(boxContent.getHeight() * lineRatio);
	}

	public void setSmallHeight(int percentage) {
		if (percentage < 0) {
			percentage = 0;
		} else if (percentage > 100) {
			percentage = 100;
		}
		this.smallHeight = bigHeight * percentage / 100;
		ensureSmallYWithinBounds();
	}

	public void setSmallY(int y) {
		this.smallY = y;
		ensureSmallYWithinBounds();
	}

	private void ensureSmallYWithinBounds() {
		if (smallY < 0) {
			smallY = 0;
		} else if (smallY + smallHeight > bigHeight) {
			smallY = bigHeight - smallHeight;
		}
	}

	public void setBigSize(int width, int height) {
		this.bigWidth = width;
		this.bigHeight = height;
		ensureSmallYWithinBounds();
	}

	public void setColors(Color bigColor, Color smallColor) {
		this.bigColor = bigColor;
		this.smallColor = smallColor;
	}
}
