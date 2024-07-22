package view.window.mainUI.component.box;

import java.awt.Graphics2D;

import utils.Globals;
import utils.Theme;

public class ContentLine implements BoxComponent {

	private static int TOP_MARGIN_DIVISOR = 12;
	private static int HEIGHT_DIVISOR = 26;
	static int TEXT_LEFT_MARGIN_DIVISOR = 20;

	private Box box;
	private int lineIndex;

	private String lineText;

	public ContentLine(Box box) {
		this.box = box;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBoxBackgroundColor());
		int locX = box.getLocX();
		int locY = box.getLocY() + (box.getHeight() / TOP_MARGIN_DIVISOR)
				+ ((box.getHeight() / HEIGHT_DIVISOR) * lineIndex);
		int width = box.getWidth();
		int height = box.getHeight() / HEIGHT_DIVISOR;
		g2d.fillRect(locX, locY, width, height);

		g2d.setColor(Theme.getPanelTextColor());
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
