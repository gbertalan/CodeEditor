package view.window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.HashSet;

import javax.swing.JFrame;

import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;
import view.window.mainUI.component.box.Box;

public class MouseListener extends MouseAdapter {
	private Listener listener;
	private Window window;
	private MainUI mainUI;
	private HashSet<UIComponent> hoveredComponents;

	public MouseListener(Listener listener) {
		this.listener = listener;
		this.window = listener.window;
		this.mainUI = listener.mainUI;
		this.hoveredComponents = listener.hoveredComponents;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		listener.unhoverAllComponents();
		mainUI.update();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (hoveredComponents.contains(mainUI.getComponent("CloseButton"))) {
			Toolkit.getDefaultToolkit().getSystemEventQueue()
					.postEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		} else if (hoveredComponents.contains(mainUI.getComponent("TrayButton"))) {
			window.setExtendedState(JFrame.ICONIFIED);
		} else if (hoveredComponents.contains(mainUI.getComponent("MaxButton"))
				|| (hoveredComponents.contains(mainUI.getComponent("TitleBar")) && e.getClickCount() == 2)) {
			toggleMaximizeWindow();
		}
	}

	private void toggleMaximizeWindow() {
		if (!window.isMaximized()) {
			window.setMaximized(true);
			listener.oldWidth = window.width;
			listener.oldHeight = window.height;
			window.width = window.getWidth();
			window.height = window.getHeight();
		} else {
			window.setMaximized(false);
			window.width = listener.oldWidth;
			window.height = listener.oldHeight;
		}
		listener.updateComponentLocationAndSize();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (hoveredComponents.contains(mainUI.getComponent("TitleBar")) && !isOnWindowControlButton()
				&& !isOnWindowEdge()) {
			listener.draggingByTitleBar = true;
			listener.initialPressOnTitleBar = e.getPoint();
		}

		if (!window.isMaximized() && isOnWindowEdge()) {
			listener.draggingByEdge = true;
			listener.edgeStart = e.getLocationOnScreen();
		}

		if (hoveredComponents.contains(mainUI.getComponent("BoxHeader")) && !listener.draggingByTitleBar
				&& !listener.draggingByEdge) {
			listener.draggingByBoxHeader = true;
			((Box) mainUI.getComponent("Box")).setMouseOffset(e.getX() - mainUI.getComponent("BoxHeader").getLocX(),
					e.getY() - mainUI.getComponent("BoxHeader").getLocY());
		}
	}

	private boolean isOnWindowControlButton() {
		return hoveredComponents.contains(mainUI.getComponent("TrayButton"))
				|| hoveredComponents.contains(mainUI.getComponent("MaxButton"))
				|| hoveredComponents.contains(mainUI.getComponent("CloseButton"));
	}

	private boolean isOnWindowEdge() {
		return hoveredComponents.contains(mainUI.getComponent("EdgeWest"))
				|| hoveredComponents.contains(mainUI.getComponent("EdgeNorth"))
				|| hoveredComponents.contains(mainUI.getComponent("EdgeEast"))
				|| hoveredComponents.contains(mainUI.getComponent("EdgeSouth"));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (listener.draggingByTitleBar)
			listener.draggingByTitleBar = false;
		if (listener.draggingByEdge) {
			updateWindowSizeAndLocation();
			listener.draggingByEdge = false;
		}
		if (listener.draggingByBoxHeader) {
			listener.draggingByBoxHeader = false;
		}
	}

	private void updateWindowSizeAndLocation() {
		window.width = window.getWidth();
		window.height = window.getHeight();
		window.locX = window.getLocation().x;
		window.locY = window.getLocation().y;
		listener.oldWidth = window.getWidth();
		listener.oldHeight = window.getHeight();
		listener.oldLocX = window.getLocation().x;
		listener.oldLocY = window.getLocation().y;
	}
}
