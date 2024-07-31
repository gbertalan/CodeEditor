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

	public static double zoomValue = 1.0;
	public static int zoomLevel = 0;

	public MouseWheelListener(Listener listener) {
		this.listener = listener;
		this.window = listener.window;
		this.mainUI = listener.mainUI;
		this.hoveredComponents = listener.hoveredComponents;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		listener.propagateEvent(e, Box::mouseWheelMoved);

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
//				box.getBoxContent().getScrollerVertical().scroll(0);
				
				// Utobbi:
//				box.getBoxController().updateContent(box, box.getBoxContent().getStartLine());
				
				
//				if (zoomLevel == -1 || zoomLevel == 0 || zoomLevel == 2 || zoomLevel == 6) // refresh at certain zoom
//																							// levels, so that it will
//																							// not be blurry
//					box.getBoxController().updateContent(box, box.getBoxContent().getStartLine());
			}

			System.out.println(ANSIText.bold("MouseWheelListener: Scroll action performed with rotation: " + zoomValue
					+ ". The zoom level is: " + zoomLevel));
		}

	}
}
