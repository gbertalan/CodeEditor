package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import utils.ANSIText;
import utils.SyntaxColor;
import utils.Theme;

public class LineTextContainerRegebbi {

	private static double TOP_MARGIN_DIVISOR = 16;
	private static double HEIGHT_DIVISOR = 36;
	static double TEXT_LEFT_MARGIN_DIVISOR = 20;
	private Box box;
	private LineNumberContainer lineNumberContainer;
	private String lineText;
	static public int limitLineLocationX;

	private String subText;
	private int locX;
	private int locY;
	private int width;
	private int height;

	private Font font;
	private int fontSize;
	private FontMetrics metrics;
	private double lineToWidthRatio;
	private boolean ratioSet;
	private double desiredLength;

	public LineTextContainerRegebbi(Box box, String lineText, LineNumberContainer lineNumberContainer) {
		System.out.println(ANSIText.red("LineTextContainer constructor is called"));
		this.box = box;
		this.lineText = lineText.replace("\t", "\u00A0\u00A0\u00A0\u00A0");
		this.lineNumberContainer = lineNumberContainer;

		updateLocationAndSize();

		// Set the font size based on initial height
		this.fontSize = (int) Math.round(box.getHeight() / HEIGHT_DIVISOR); // You might want to adjust this divisor
		this.font = new Font("Consolas", Font.BOLD, fontSize);

		// Initialize subText here since we need metrics in draw() to be accurate
		this.subText = "";
	}

	private void updateLocationAndSize() {
		locX = lineNumberContainer.getLocX() + lineNumberContainer.getWidth();
		locY = lineNumberContainer.getLocY();
		width = box.getWidth() - lineNumberContainer.getWidth();
		height = box.getHeight();
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBoxBackgroundColor());
		updateLocationAndSize();
		g2d.fillRect(locX, locY, width, height);

		g2d.setColor(SyntaxColor.getDefaultTextColor());
		g2d.setFont(font);

		// Ensure metrics is initialized correctly with the current graphics context
		metrics = g2d.getFontMetrics(font);

		// Calculate subText with the correct font metrics
		if (!ratioSet) {
			this.subText = getSubText(g2d);
		}

		if (!ratioSet) {
			this.lineToWidthRatio = (double) metrics.stringWidth(subText) / this.width;
			ratioSet = true;
		}

		adjustFontSize(g2d);

		int textHeight = locY + metrics.getAscent();
		g2d.drawString(subText, locX, textHeight); // Adjust y-coordinate as needed

		System.out.println("g2d.getFont().getSize(): " + g2d.getFont().getSize());
	}

	private void adjustFontSize(Graphics2D g2d) {
		desiredLength = width * lineToWidthRatio;
		int textWidth = metrics.stringWidth(subText);

		while (textWidth > desiredLength && fontSize > 1) {
			fontSize--;
			font = new Font("Consolas", Font.BOLD, fontSize);
			g2d.setFont(font);
			metrics = g2d.getFontMetrics(font); // Recalculate metrics for new font size
			textWidth = metrics.stringWidth(subText);
		}

		while (textWidth < desiredLength) {
			fontSize++;
			font = new Font("Consolas", Font.BOLD, fontSize);
			g2d.setFont(font);
			metrics = g2d.getFontMetrics(font); // Recalculate metrics for new font size
			textWidth = metrics.stringWidth(subText);

			// Stop if the text width exceeds the container width
			if (textWidth > width) {
				fontSize--;
				font = new Font("Consolas", Font.BOLD, fontSize);
				g2d.setFont(font);
				metrics = g2d.getFontMetrics(font); // Recalculate metrics for new font size
				break;
			}
		}
	}

	private String getSubText(Graphics2D g2d) {

		double maxTextWidth = width;

		String truncatedText = lineText;
		if (metrics.stringWidth(lineText) > maxTextWidth) {
			String ellipsis = "...";
			int ellipsisWidth = metrics.stringWidth(ellipsis);
			int textWidth = metrics.stringWidth(lineText);

			while (textWidth + ellipsisWidth > maxTextWidth && truncatedText.length() > 0) {
				truncatedText = truncatedText.substring(0, truncatedText.length() - 1);
				textWidth = metrics.stringWidth(truncatedText);
			}

			truncatedText += ellipsis;
		}

		return truncatedText;
	}
}
