package view.window;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashSet;

import utils.ANSIText;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;
import view.window.mainUI.component.box.Box;

public class MouseWheelListener extends MouseAdapter {
	private Listener listener;
	private Window window;
	private MainUI mainUI;
	private HashSet<UIComponent> hoveredComponents;

	private static final double INITIAL_ZOOM_VALUE = 1.0;
	private static final int INITIAL_ZOOM_LEVEL = 0;

	public static double zoomValue = INITIAL_ZOOM_VALUE;
	public static int zoomLevel = INITIAL_ZOOM_LEVEL;

	public MouseWheelListener(Listener listener) {
		listener.setMouseWheelListener(this);
		this.listener = listener;
		this.window = listener.window;
		this.mainUI = listener.mainUI;
		this.hoveredComponents = listener.hoveredComponents;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		listener.propagateEvent(e, Box::mouseWheelMoved);

		setZoom(e);

	}

	private void setZoom(MouseWheelEvent e) {
		if (e.isControlDown()) {
			zoomValue += ((double) e.getWheelRotation() * 0.01f);

			if (e.getWheelRotation() > 0) {
				zoomValue = 0.8;
				++zoomLevel;
			} else {
				zoomValue = 1.25;
				--zoomLevel;
			}

			if (zoomLevel > 10) {
				zoomValue = 1.0;
				zoomLevel = 10;
			} else if (zoomLevel < -1) {
				zoomValue = 1.0;
				zoomLevel = -1;
			}

			for (Box box : window.getMainUI().getBoxList()) {
				box.zoom(e.getPoint());
			}

			System.out.println(ANSIText.bold("MouseWheelListener: Scroll action performed with rotation: " + zoomValue
					+ ". The zoom level is: " + zoomLevel));
		}
	}

	public void resetZoom() {
		while (zoomLevel != INITIAL_ZOOM_LEVEL) {
			int wheelRotation;
			if (zoomLevel > INITIAL_ZOOM_LEVEL) {
				wheelRotation = -1; // Scroll up to decrease zoom level
				zoomValue = 1.25;
			} else {
				wheelRotation = 1; // Scroll down to increase zoom level
				zoomValue = 0.8;
			}

			// Create a synthetic MouseWheelEvent
			MouseWheelEvent syntheticEvent = new MouseWheelEvent(window, MouseWheelEvent.MOUSE_WHEEL,
					System.currentTimeMillis(), MouseWheelEvent.CTRL_DOWN_MASK, window.width / 2, window.height / 2,
					0, false, MouseWheelEvent.WHEEL_UNIT_SCROLL, 1, wheelRotation);

			setZoom(syntheticEvent);
		}

		// Ensure the final zoomValue is set to the initial zoom value
		zoomValue = INITIAL_ZOOM_VALUE;

		// Update the UI boxes to reflect the reset zoom state
//        Point centerPoint = new Point(window.getWidth() / 2, window.getHeight() / 2);
		Point centerPoint = new Point(window.width / 2, window.height / 2);
		for (Box box : window.getMainUI().getBoxList()) {
			box.zoom(centerPoint);
		}

		System.out.println(ANSIText.bold("Zoom reset to initial values. The zoom level is: " + zoomLevel));
	}
}
