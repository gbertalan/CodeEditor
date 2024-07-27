package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Theme;

public class ScrollerHorizontal {

	private int bigHeight;
	private int bigWidth;
	private int smallWidth = 50;
	private int smallX = 0;
	private Color bigColor = Theme.getScrollBarColorBig();
	private Color smallColor = Theme.getScrollBarColorSmall();

	private BoxContent boxContent;

	public ScrollerHorizontal(BoxContent boxContent) {
		this.boxContent = boxContent;
	}

	public void draw(Graphics2D g2d) {

		// Draw the big rectangle
		this.bigHeight = (boxContent.getBox().getHeight() / 16) - 2;
		this.bigWidth = boxContent.getWidth();
		g2d.setColor(bigColor);
		int bigLocX = boxContent.getLocX()+boxContent.getLineNumberContainerWidth();
		int bigLocY = boxContent.getLocY() + boxContent.getHeight() - bigHeight;
		g2d.fillRect(bigLocX, bigLocY, bigWidth, bigHeight);

		// Draw the small rectangle
		calculateSmallWidth();
		g2d.setColor(smallColor);
		g2d.fillRect(bigLocX + smallX, bigLocY, smallWidth, bigHeight);

		// top border:
		g2d.setColor(Theme.getSeparatorLineColor());
//		g2d.drawLine(bigLocX, bigLocY, bigWidth-boxContent.getLineNumberContainerWidth(), bigLocY);
		g2d.drawRect(bigLocX, bigLocY, bigWidth-boxContent.getLineNumberContainerWidth(), bigHeight);
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
}
