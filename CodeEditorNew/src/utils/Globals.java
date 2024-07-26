package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Globals {

	public static void setRenderingHints(Graphics2D g2d) {
		/*
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON);
		 * g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
		 * RenderingHints.VALUE_RENDER_QUALITY);
		 * g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
		 * RenderingHints.VALUE_STROKE_PURE);
		 */
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	public static boolean cursorAtEdgeOfPanel(Point cursorPosition, JPanel panel) {
		int edgeSize = 5;
		if (cursorPosition.x < edgeSize || cursorPosition.y < edgeSize
				|| cursorPosition.x > (panel.getWidth() - edgeSize)
				|| cursorPosition.y > (panel.getHeight() - edgeSize))
			return true;
		else
			return false;
	}

	/**
	 * Gets the screen bounds for the screen that is currently displaying the given
	 * component.
	 * 
	 * @param component
	 * @return
	 */
	public static Rectangle getScreenSize(Component component) {
		if (component == null) {
			throw new IllegalArgumentException("Component cannot be null");
		}

		// Get the GraphicsConfiguration of the component
		GraphicsConfiguration graphicsConfiguration = component.getGraphicsConfiguration();
		if (graphicsConfiguration == null) {
			// If the component is not visible, fallback to the default screen device
			graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();
		}

		// Get the GraphicsDevice from the GraphicsConfiguration
		GraphicsDevice graphicsDevice = graphicsConfiguration.getDevice();

		// Return the bounds of the GraphicsDevice, which represent the screen size
		return graphicsDevice.getDefaultConfiguration().getBounds();
	}

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {

		}
	}

	/**
	 * Returns the y coordinate of the text so that it is vertically centered to its
	 * containing component
	 * 
	 * @param g2d
	 * @param text
	 * @param font
	 * @param containerHeight
	 * @return
	 */
	public static int centerTextVert(Graphics2D g2d, String text, Font font, int containerHeight) {
		g2d.setFont(font);

		// Calculate the font metrics
		FontMetrics metrics = g2d.getFontMetrics(font);
		int textAscent = metrics.getAscent();
		return (containerHeight) / 2 + (textAscent / 2);
	}

	/**
	 * Centers the text vertically and draws it.
	 * 
	 * @param g2d
	 * @param x
	 * @param y
	 * @param leftMargin
	 * @param containerWidth
	 * @param containerHeight
	 * @param text
	 */
	public static void drawVerticallyCenteredText(Graphics2D g2d, int x, int y, double leftMargin, int containerWidth,
			int containerHeight, String text) {

		if (text != null) {

			int fontSize = (int) Math.round(containerHeight / 1.5);

			Font font = new Font("Consolas", Font.BOLD, fontSize);
			g2d.setFont(font);

//			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			FontMetrics metrics = g2d.getFontMetrics(font);
			double maxTextWidth = containerWidth - 2 * leftMargin;

			String truncatedText = text;

			if (metrics.stringWidth(text) > maxTextWidth) {
				String ellipsis = "...";
				int ellipsisWidth = metrics.stringWidth(ellipsis);
				int textWidth = metrics.stringWidth(text);

				while (textWidth + ellipsisWidth > maxTextWidth && truncatedText.length() > 0) {
					truncatedText = truncatedText.substring(0, truncatedText.length() - 1);
					textWidth = metrics.stringWidth(truncatedText);
				}

				truncatedText += ellipsis;
			}

			int xx = (int) Math.round(x + leftMargin);
			int yy = (int) Math.round(y + containerHeight - (containerHeight / 3.5));

			g2d.drawString(truncatedText, xx, yy);
		}
	}

	/**
	 * Draws a centered text string within a specified component.
	 * 
	 * This method draws the given text string such that it is horizontally and
	 * vertically centered within a containing component of specified width and
	 * height. If the text exceeds the component's width, it will be truncated and
	 * appended with an ellipsis ("...").
	 * 
	 * @param g2d             The Graphics2D context to draw on.
	 * @param x               The x-coordinate of the containing component's
	 *                        top-left corner.
	 * @param y               The y-coordinate of the containing component's
	 *                        top-left corner.
	 * @param containerWidth  The width of the containing component.
	 * @param containerHeight The height of the containing component.
	 * @param text            The text string to be drawn.
	 */
	public static void drawCenteredText(Graphics2D g2d, int x, int y, int containerWidth, int containerHeight,
			String text) {

		if (text != null) {

			int fontSize = (int) Math.round(containerHeight / 1.0);
			Font font = new Font("Consolas", Font.BOLD, fontSize);
			g2d.setFont(font);

			FontMetrics metrics = g2d.getFontMetrics(font);
			double maxTextWidth = containerWidth;

			String truncatedText = text;
			if (metrics.stringWidth(text) > maxTextWidth) {
				String ellipsis = "...";
				int ellipsisWidth = metrics.stringWidth(ellipsis);
				int textWidth = metrics.stringWidth(text);

				while (textWidth + ellipsisWidth > maxTextWidth && truncatedText.length() > 0) {
					truncatedText = truncatedText.substring(0, truncatedText.length() - 1);
					textWidth = metrics.stringWidth(truncatedText);
				}

				truncatedText += ellipsis;
			}

			// Calculate the x coordinate for horizontally centering the text
			int textWidth = metrics.stringWidth(truncatedText);
			int xx = (int) Math.round(x + (containerWidth - textWidth) / 2.0);

			// Calculate the y coordinate for vertically centering the text
			int textHeight = metrics.getAscent();
			int yy = y + textHeight;

			g2d.drawString(truncatedText, xx, yy);
		}
	}

	public static void drawLeftAlignedText(Graphics2D g2d, int x, int y, int containerWidth, int containerHeight,
			String text) {
		if (text != null) {

			int fontSize = (int) Math.round(containerHeight / 1.0);
			Font font = new Font("Consolas", Font.BOLD, fontSize);
			g2d.setFont(font);

			FontMetrics metrics = g2d.getFontMetrics(font);
			double maxTextWidth = containerWidth;

			String truncatedText = text;
			if (metrics.stringWidth(text) > maxTextWidth) {
				String ellipsis = "...";
				int ellipsisWidth = metrics.stringWidth(ellipsis);
				int textWidth = metrics.stringWidth(text);

				while (textWidth + ellipsisWidth > maxTextWidth && truncatedText.length() > 0) {
					truncatedText = truncatedText.substring(0, truncatedText.length() - 1);
					textWidth = metrics.stringWidth(truncatedText);
				}

				truncatedText += ellipsis;
			}

			int xx = x;

			// Calculate the y coordinate for vertically centering the text
			int textHeight = metrics.getAscent();
			int yy = y + textHeight;

			g2d.drawString(truncatedText, xx, yy);
		}
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

}
