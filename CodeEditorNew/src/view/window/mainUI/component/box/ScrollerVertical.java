package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import utils.Theme;

public class ScrollerVertical {

	private int bigWidth;
	private int bigHeight;
	private int smallHeight = 50;
	private int smallY = 0;
	private Color bigColor = Theme.getScrollBarColorBig();
	private Color smallColor = Theme.getScrollBarColorSmall();

	private BoxContent boxContent;
	private int bigLocX;
	private int bigLocY;

	public ScrollerVertical(BoxContent boxContent) {
		this.boxContent = boxContent;
	}

	public void draw(Graphics2D g2d) {

		// Draw the big rectangle
		this.bigWidth = (boxContent.getBox().getHeight() / 46) - 2;
		this.bigHeight = boxContent.getHeight();
		g2d.setColor(bigColor);
		bigLocX = boxContent.getLocX() + boxContent.getWidth() - bigWidth - 1;
		bigLocY = boxContent.getLocY();
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

	public int getLocX() {
		return bigLocX;
	}

	public int getLocY() {
		return bigLocY;
	}

	public int getWidth() {
		return bigWidth;
	}

	public int getHeight() {
		return bigHeight;
	}

	public void scroll(int value) {
		int scrollFactor = 5;
	    smallY += value * scrollFactor;
	    if (smallY < 0) {
	        smallY = 0;
	    } else if (smallY + smallHeight > bigHeight) {
	        smallY = bigHeight - smallHeight;
	    }
	}


	public void mouseMoved(MouseEvent e) {
		System.out.println("Mouse in scrollVertical moved.");
//		for (int i = 0; i < boxContent.getNoOfDisplayedLines(); i++) {
//			boxContent.getDisplayedLine(i).getLineNumberContainer().scrollUp();
//		}
//
//		boxContent.getBox().repaint();
	}
}
