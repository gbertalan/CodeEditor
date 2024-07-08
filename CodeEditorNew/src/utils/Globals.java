package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

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
}
