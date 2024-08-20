package view.window.mainUI.component.box;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Set;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import utils.ANSIText;
import utils.SyntaxColor;

public class LineTextContainer {

	private static final double HEIGHT_DIVISOR = 36;

	private Box box;
	private LineNumberContainer lineNumberContainer;
	private String lineText;
	private int locX;
	private int locY;
	private int width;
	private int height;

	private Font font;
	private FontMetrics metrics;

	private int scrollHorizontal;

	private int scrollVertical;
	private SyntaxHighlight syntaxHighlight;
	private List<Map.Entry<String,Color>> coloredText;

	public LineTextContainer(Box box, String text, LineNumberContainer lineNumberContainer) {
		this.box = box;
		this.lineText = text.replace("\t", "\u00A0\u00A0\u00A0\u00A0");
		this.lineNumberContainer = lineNumberContainer;

		updateLocationAndSize();
		this.font = new Font("Consolas", Font.BOLD, (int) Math.round(box.getHeight() / HEIGHT_DIVISOR));

		this.syntaxHighlight = new SyntaxHighlight(lineText);
		coloredText = syntaxHighlight.getColoredText();
	}

	private void updateLocationAndSize() {
		locX = lineNumberContainer.getLocX() + lineNumberContainer.getWidth() + scrollHorizontal;
		locY = lineNumberContainer.getLocY() + scrollVertical;
		width = box.getWidth() - lineNumberContainer.getWidth();
		height = box.getHeight();
	}

	public void draw(Graphics2D g2d) {

		updateLocationAndSize();
		g2d.setFont(font);

		if (metrics == null) { metrics = g2d.getFontMetrics(font); }
		int drawX = locX;
		int textHeight = locY + metrics.getAscent();
		
		for (Map.Entry<String, Color> entry : coloredText) {
		    String token = entry.getKey();
		    Color color = entry.getValue();
		    
		    g2d.setColor(color);
		    g2d.drawString(token, drawX, textHeight);
		    drawX += metrics.stringWidth(token);
		}

	}

	public void setScrollHorizontal(int value) {
		this.scrollHorizontal = value;
	}

	public void setText(String line) {
		this.lineText = line;
	}
}
