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

	public MouseWheelListener(Listener listener) {
		this.listener = listener;
		this.window = listener.window;
		this.mainUI = listener.mainUI;
		this.hoveredComponents = listener.hoveredComponents;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// Handle the mouse wheel movement
		if (hoveredComponents != null) {
			for (UIComponent component : hoveredComponents) {
				// Example: If you have a method to handle scroll in your UIComponent
//				component.handleScroll(e);
			}
		}

//		zoomValue = ((double) e.getWheelRotation() * 0.01f) +1;
		zoomValue += ((double) e.getWheelRotation() * 0.01f);
		
//		zoomValue = (double) Math.max(0.1, Math.min(3.0, ((double) e.getWheelRotation() * 0.01) + 1));


		Box box0 = (Box) window.getMainUI().getComponent("Box0");
		box0.zoom(e.getPoint());

		System.out.println(ANSIText.bold("MouseWheelListener: Scroll action performed with rotation: " + zoomValue));

	}
}
