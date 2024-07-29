package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Theme;

public class ScrollerVertical {

	private int bigWidth;
	private int bigHeight;
	private int smallHeight;
	private int smallY;
	private Color bigColor = Theme.getScrollBarColorBig();
	private Color smallColor = Theme.getScrollBarColorSmall();
	private int yShift;

	private BoxContent boxContent;
	private double yShiftRatio;
	private int scrollSpeed = 7;
	private double scrollPosition;
	private int startLine;

	public ScrollerVertical(BoxContent boxContent) {
		this.boxContent = boxContent;
	}

	public void draw(Graphics2D g2d) {

		// Draw the big rectangle
		this.bigWidth = (boxContent.getBox().getHeight() / 46) - 2;
		this.bigHeight = boxContent.getHeight() - bigWidth;
		g2d.setColor(bigColor);
		int bigLocX = boxContent.getLocX() + boxContent.getWidth() - bigWidth;
		int bigLocY = boxContent.getLocY();
		g2d.fillRect(bigLocX, bigLocY, bigWidth, bigHeight);

		// Draw the small rectangle
		calculateSmallHeight();
		updateYShift();
		g2d.setColor(smallColor);
		g2d.fillRect(bigLocX, bigLocY + smallY + yShift, bigWidth, smallHeight);

		// left border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(bigLocX, bigLocY, bigLocX, bigLocY + boxContent.getHeight() - bigWidth);
	}

	public void calculateSmallHeight() {
		double lineRatio;
		if (boxContent.getNoOfAllLines() < boxContent.getNoOfDisplayedLines()) {
			lineRatio = 1.0;
		}
		lineRatio = (double) (boxContent.getNoOfDisplayedLines()) / boxContent.getNoOfAllLines();

		smallHeight = (int) Math.round(boxContent.getHeight() * lineRatio);
	}

	public void updateYShift() {
		yShift = (int) (bigHeight * yShiftRatio);
	}

	public void scroll(int unitsToScroll) {
		yShift += (unitsToScroll * scrollSpeed);

		if (yShift < 0) {
			yShift = 0;
		} else if (yShift + smallHeight > bigHeight) {
			yShift = bigHeight - smallHeight;
		}

		yShiftRatio = 1 - (double) (bigHeight - yShift) / bigHeight;

		calculateScrollPosition();
		calculateStartLine();
		boxContent.getBox().getBoxController().updateContent(boxContent.getBox(), startLine);
	}

	/**
	 * Calculates the scrollPosition, which is a number between 0 (scroll thumb is
	 * fully up) and 1 (scroll thumb is fully down).
	 */
	private void calculateScrollPosition() {
		scrollPosition = yShift / (double) (bigHeight - smallHeight);
	}

	private void calculateStartLine() {
		startLine = (int) Math
				.round(scrollPosition * (boxContent.getNoOfAllLines() - boxContent.getNoOfDisplayedLines()));
	}
}