package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import utils.Theme;

public class ScrollerHorizontal {

	private int bigHeight;
	private int bigWidth;
	private int smallWidth = 50;
	private int smallX = 0;
	private Color bigColor = Theme.getScrollBarColorBig();
	private Color smallColor = Theme.getScrollBarColorSmall();

	private BoxContent boxContent;
	private int bigLocX;
	private int bigLocY;

	public ScrollerHorizontal(BoxContent boxContent) {
		this.boxContent = boxContent;
	}

	public void draw(Graphics2D g2d) {

		// Draw the big rectangle
		this.bigHeight = (boxContent.getBox().getHeight() / 46) - 2;
		this.bigWidth = boxContent.getWidth()-boxContent.getLineNumberContainerWidth();
		g2d.setColor(bigColor);
		bigLocX = boxContent.getLocX() + boxContent.getLineNumberContainerWidth();
		bigLocY = boxContent.getLocY() + boxContent.getHeight() - bigHeight;
		g2d.fillRect(bigLocX, bigLocY, bigWidth, bigHeight);

		// Draw the small rectangle
		calculateSmallWidth();
		g2d.setColor(smallColor);
		g2d.fillRect(bigLocX + smallX, bigLocY, smallWidth, bigHeight);

		// top border:
		g2d.setColor(Theme.getSeparatorLineColor());
//		g2d.drawLine(bigLocX, bigLocY, bigLocX+bigWidth, bigLocY);
		g2d.drawRect(bigLocX, bigLocY, bigWidth, bigHeight);
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
		smallX += value * scrollFactor;
		if (smallX < 0) {
			smallX = 0;
		} else if (smallX + smallWidth > bigWidth) {
			smallX = bigWidth - smallWidth;
		}
	}

	public void mouseMoved(MouseEvent e) {
		System.out.println("Mouse in scrollHorizontal moved.");
	}
}
