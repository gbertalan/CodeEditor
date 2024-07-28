package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Theme;

public class ScrollerHorizontal {

	private int bigWidth;
	private int bigHeight;
	private int smallWidth;
	private int smallX;
	private Color bigColor = Theme.getScrollBarColorBig();
	private Color smallColor = Theme.getScrollBarColorSmall();
	private int xShift;

	private BoxContent boxContent;
	private double xShiftRatio;
	private int scrollSpeed = 5;

	public ScrollerHorizontal(BoxContent boxContent) {
		this.boxContent = boxContent;
	}

	public void draw(Graphics2D g2d) {

		// Draw the big rectangle
		this.bigHeight = (boxContent.getBox().getHeight() / 46) - 2;
		this.bigWidth = boxContent.getWidth() - boxContent.getLineNumberContainerWidth() - bigHeight;
		g2d.setColor(bigColor);
		int bigLocX = boxContent.getLocX() + boxContent.getLineNumberContainerWidth();
		int bigLocY = boxContent.getLocY() + boxContent.getHeight() - bigHeight;
		g2d.fillRect(bigLocX, bigLocY, bigWidth, bigHeight);

		// Draw the small rectangle
		calculateSmallWidth();
		updateXShift();
		g2d.setColor(smallColor);
		g2d.fillRect(bigLocX + smallX + xShift, bigLocY, smallWidth, bigHeight);

		// top border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(bigLocX, bigLocY, bigLocX + boxContent.getWidth() -boxContent.getLineNumberContainerWidth() - bigHeight, bigLocY);
		g2d.drawLine(bigLocX, bigLocY, bigLocX, bigLocY+bigHeight);
	}

	public void calculateSmallWidth() {
		double lineRatio;
		if (boxContent.getNoOfAllColumns() < boxContent.getNoOfDisplayedColumns()) {
			lineRatio = 1.0;
		} else {
			lineRatio = (double) (boxContent.getNoOfDisplayedColumns()) / boxContent.getNoOfAllColumns();
		}

		smallWidth = (int) Math.round(boxContent.getWidth() * lineRatio);
	}

	public void updateXShift() {
		xShift = (int) (bigWidth * xShiftRatio);
	}

	public void scroll(int unitsToScroll) {
		xShift += (unitsToScroll * scrollSpeed);

		if (xShift < 0) {
			xShift = 0;
		} else if (xShift + smallWidth > bigWidth) {
			xShift = bigWidth - smallWidth;
		}

		xShiftRatio = 1 - (double) (bigWidth - xShift) / bigWidth;
	}
}
