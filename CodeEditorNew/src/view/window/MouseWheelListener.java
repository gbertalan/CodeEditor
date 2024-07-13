package view.window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashSet;

import utils.ANSIText;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;

public class MouseWheelListener extends MouseAdapter {
	private Listener listener;
	private Window window;
	private MainUI mainUI;
	private HashSet<UIComponent> hoveredComponents;

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

		System.out.println(
				ANSIText.bold("MouseWheelListener: Scroll action performed with rotation: " + e.getWheelRotation()));

	}
}
