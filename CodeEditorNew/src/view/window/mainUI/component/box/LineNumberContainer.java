package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Globals;
import utils.SyntaxColor;
import utils.Theme;

public class LineNumberContainer implements BoxComponent {

	private static double TOP_MARGIN_DIVISOR = 16;
	private static double HEIGHT_DIVISOR = 36;
	static double TEXT_LEFT_MARGIN_DIVISOR = 20;

	private Box box;
	private int lineNumber;
	private int lineIndex;
	private int locX;
	private int locY;
	private int width;
	private int height;

	public LineNumberContainer(Box box, Integer lineNumber, int lineIndex) {
		this.box = box;
		if (!lineNumber.equals(null))
			this.lineNumber = lineNumber;
		
		this.lineIndex = lineIndex;
	}

	@Override
	public void draw(Graphics2D g2d) {
//		g2d.setColor(Theme.getBoxBackgroundColor());
		locX = box.getLocX();
		locX = 0;
		locY = (int) Math.round(box.getLocY() + (box.getHeight() / TOP_MARGIN_DIVISOR)
				+ ((box.getHeight() / HEIGHT_DIVISOR) * lineIndex));
		locY = (int) Math.round((box.getHeight() / HEIGHT_DIVISOR) * lineIndex);
		width = box.getWidth() / 8;
		height = (int) Math.round(box.getHeight() / HEIGHT_DIVISOR);
//		g2d.fillRect(locX, locY, width, height);

		g2d.setColor(SyntaxColor.getLineNumberColor());
		Globals.drawCenteredText(g2d, locX, locY, width, height, Integer.toString(lineNumber));
	}

	public void setLineNumber(int number) {
		this.lineNumber = number;
	}

	public void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public int getLineIndex() {
		return lineIndex;
	}

}
