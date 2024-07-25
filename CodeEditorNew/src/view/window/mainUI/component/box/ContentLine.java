package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Globals;
import utils.Theme;

public class ContentLine implements BoxComponent {

	private static double TOP_MARGIN_DIVISOR = 16;
	private static double HEIGHT_DIVISOR = 26;
	static double TEXT_LEFT_MARGIN_DIVISOR = 20;

	private Box box;
	private int lineIndex; // the index of the drawn line

	private String lineText;
	private LineNumberContainer lineNumberContainer; // the index as it would be indexed in a file
	private int startFileLineIndex;

	public ContentLine(Box box, int startFileLineIndex) {
		this.box = box;
		this.startFileLineIndex = startFileLineIndex;
	}

	@Override
	public void draw(Graphics2D g2d) {

		lineNumberContainer = new LineNumberContainer(box, null, 0);

		lineNumberContainer.setLineNumber(startFileLineIndex);
		lineNumberContainer.draw(g2d);

		g2d.setColor(Theme.getBoxBackgroundColor());
		g2d.setColor(Color.GREEN);
		int locX = lineNumberContainer.getLocX() + lineNumberContainer.getWidth();
		int locY = (int) Math.round(box.getLocY() + (box.getHeight() / TOP_MARGIN_DIVISOR)
				+ ((box.getHeight() / HEIGHT_DIVISOR) * lineIndex));
		int width = box.getWidth() - lineNumberContainer.getWidth();
		int height = (int) Math.round(box.getHeight() / HEIGHT_DIVISOR);
		g2d.fillRect(locX, locY, width, height);

		g2d.setColor(Theme.getPanelTextColor());
		Globals.drawVerticallyCenteredText(g2d, locX, locY, (box.getWidth() / TEXT_LEFT_MARGIN_DIVISOR), width, height,
				lineText);
	}

	public void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
	}

	public void setlineText(String lineText) {
		this.lineText = lineText;

	}

	public void incrementLineIndex() {
		++lineIndex;
	}

	public int getLineIndex() {
		return lineIndex;
	}

}
