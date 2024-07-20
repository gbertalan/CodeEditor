package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Globals;

public class ContentLine implements BoxComponent {

	private static int HEIGHT_DIVISOR = 16;
	private static int CONTENT_LINE_HEIGHT;
	private static int TEXT_LEFT_MARGIN_DIVISOR = 20;

	private Box box;
	private String line;
	private int lineIndex;

	private String lineText;

	public ContentLine(Box box) {
		this.box = box;
		CONTENT_LINE_HEIGHT = box.getBoxHeader().getHeight();
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		int locX = box.getLocX();
		int locY = box.getLocY() + CONTENT_LINE_HEIGHT + (CONTENT_LINE_HEIGHT * lineIndex);
		int width = box.getWidth();
		int height = CONTENT_LINE_HEIGHT;
		g2d.fillRect(locX, locY, width, height);
		
		g2d.setColor(Color.CYAN);
		Globals.drawCenteredText(g2d, locX, locY, box.getWidth() / TEXT_LEFT_MARGIN_DIVISOR, box.getWidth(),
				box.getHeight() / HEIGHT_DIVISOR, lineText);
	}

	public void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
	}

	public void setlineText(String lineText) {
		this.lineText = lineText;

	}

}
