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
		if (hoveredComponents.contains(listener.closeButton)) {
			listener.getControl().closeApp();
		} else if (hoveredComponents.contains(listener.trayButton)) {
			window.setExtendedState(JFrame.ICONIFIED);
		} else if (hoveredComponents.contains(listener.maxButton)
				|| (hoveredComponents.contains(listener.titleBar) && e.getClickCount() == 2)) {
			toggleMaximizeWindow();
		}

		listener.propagateEvent(e, Box::mouseClicked);
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
		if (hoveredComponents.contains(listener.titleBar) && !isOnWindowControlButton() && !isOnWindowEdge()) {
			listener.draggingByTitleBar = true;
			listener.initialPressOnTitleBar = e.getPoint();
		}

		if (!window.isMaximized() && isOnWindowEdge()) {
			listener.draggingByEdge = true;
			listener.edgeStart = e.getLocationOnScreen();
		}

		if (!listener.draggingByTitleBar && !listener.draggingByEdge) {

			int noOfBoxes = listener.boxController.getNoOfBoxes();
			System.out.println("noOfBoxes: " + noOfBoxes);
			for (Box box : listener.boxController.getBoxMap().values()) {
				if (hoveredComponents.contains(box)) {
					listener.draggingByBoxHeader = true;
					listener.dragginBoxID = box.getId();
					box.setMouseOffset(e.getX() - box.getLocX(), e.getY() - box.getLocY());
					break;
				}
			}
		}

		if (listener.getTopHoveredComponent().equals(listener.background) && hoveredComponents.size() == 1) {
			System.out.println("THIS");
			listener.draggingByBackground = true;
			for (Box box : listener.boxController.getBoxMap().values()) {
				box.setMouseOffset(e.getX() - box.getLocX(), e.getY() - box.getLocY());
			}
		}
	}

	private boolean isOnWindowControlButton() {
		return hoveredComponents.contains(listener.trayButton) || hoveredComponents.contains(listener.maxButton)
				|| hoveredComponents.contains(listener.closeButton);
	}

	private boolean isOnWindowEdge() {
		return hoveredComponents.contains(listener.edgeWest) || hoveredComponents.contains(listener.edgeNorth)
				|| hoveredComponents.contains(listener.edgeEast) || hoveredComponents.contains(listener.edgeSouth);
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
		if (listener.draggingByBackground) {
			listener.draggingByBackground = false;
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
