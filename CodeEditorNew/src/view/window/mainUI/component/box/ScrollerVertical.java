package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Theme;

public class ScrollerVertical {

	private int bigWidth;
	private int bigHeight;
	private int smallHeight = 50;
	private int smallY = 0;
	private Color bigColor = Theme.getScrollBarColorBig();
	private Color smallColor = Theme.getScrollBarColorSmall();

	private BoxContent boxContent;

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
}
