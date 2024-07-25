package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Globals;
import utils.SyntaxColor;
import utils.Theme;

public class LineTextContainer {

	private static double TOP_MARGIN_DIVISOR = 16;
	private static double HEIGHT_DIVISOR = 36;
	static double TEXT_LEFT_MARGIN_DIVISOR = 20;
	private Box box;
	private LineNumberContainer lineNumberContainer;
	private String lineText;
	static public int limitLineLocationX;

	public LineTextContainer(Box box, String lineText, LineNumberContainer lineNumberContainer) {
		this.box = box;
		this.lineText = lineText.replace("\t", "\u00A0\u00A0\u00A0\u00A0");
		this.lineNumberContainer = lineNumberContainer;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBoxBackgroundColor());

		int locX = lineNumberContainer.getLocX() + lineNumberContainer.getWidth();
		int locY = lineNumberContainer.getLocY();
		int width = box.getWidth() - lineNumberContainer.getWidth();
		int height = lineNumberContainer.getHeight();
		g2d.fillRect(locX, locY, width, height);

		g2d.setColor(SyntaxColor.getDefaultTextColor());
		Globals.drawLeftAlignedText(g2d, locX, locY, width, height, lineText);

		// limit line:
//		g2d.setColor(SyntaxColor.getDefaultTextColor());
//		limitLineLocationX = box.getLocX() + box.getWidth() - (box.getWidth() / 20);
//		g2d.drawLine(limitLineLocationX, locY, limitLineLocationX, locY + height);
	}

}
