package view.window.mainUI.component.box;

import java.awt.Graphics2D;
import java.util.ArrayList;

import utils.Theme;

public class BoxContent implements BoxComponent {

	private Box box;

	private int locX;
	private int locY;
	private int width;
	private int height;

	private ArrayList<DisplayedLine> displayedLines;


	public BoxContent(Box box, ArrayList<String> fileLineList, int startFileLineIndex) {
		this.box = box;
		this.displayedLines = new ArrayList<>();

		// create displayed lines, unbroken:
		int lineIndex = 0;
		for (String fileLine : fileLineList) {
			displayedLines.add(new DisplayedLine(box, startFileLineIndex + lineIndex + 1, fileLine, lineIndex));
			++lineIndex;
		}
	}

	private void updateLocationAndSize() {
		locX = box.getLocX();
		locY = box.getLocY() + box.getBoxHeader().getHeight();
		width = box.getWidth();
		height = box.getHeight() - box.getBoxHeader().getHeight();
	}

	@Override
	public void draw(Graphics2D g2d) {

		// background:
		g2d.setColor(Theme.getBoxBackgroundColor());
		updateLocationAndSize();
		g2d.fillRect(locX, locY, width, height);

		// displayed lines:

		for (DisplayedLine displayedLine : displayedLines) {
			displayedLine.draw(g2d);
		}
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
