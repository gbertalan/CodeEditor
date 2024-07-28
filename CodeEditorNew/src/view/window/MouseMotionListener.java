package view.window;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import view.window.Window;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.CloseButton;
import view.window.mainUI.component.UIComponent;
import view.window.mainUI.component.box.Box;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import utils.ANSIText;

public class MouseMotionListener extends MouseMotionAdapter {
	private Listener listener;
	private Window window;
	private MainUI mainUI;
	private HashSet<UIComponent> hoveredComponents;

	public MouseMotionListener(Listener listener) {
		this.listener = listener;
		this.window = listener.window;
		this.mainUI = listener.mainUI;
		this.hoveredComponents = listener.hoveredComponents;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (listener.draggingByTitleBar) {
			handleTitleBarDrag(e);
		} else if (listener.draggingByEdge) {
			handleEdgeDrag(e);
		} else if (listener.draggingByBoxHeader) {
			handleBoxDrag(e);
		}

	}

	private void handleTitleBarDrag(MouseEvent e) {
		window.width = listener.oldWidth;
		window.height = listener.oldHeight;
		window.locX = listener.oldLocX;
		window.locY = listener.oldLocY;

		if (!window.isMaximized()) {
			Point mouseLocation = e.getLocationOnScreen();
			int x = mouseLocation.x - listener.initialPressOnTitleBar.x;
			int y = mouseLocation.y - listener.initialPressOnTitleBar.y;

			window.setLocation(x, y);
			window.locX = x;
			window.locY = y;
			listener.oldLocX = x;
			listener.oldLocY = y;
		} else {
			double ratio = e.getX() / (double) window.getWidth();
			window.setSize(window.width, window.height);
			listener.initialPressOnTitleBar = new Point((int) (window.width * ratio), e.getY());
			listener.updateComponentLocationAndSize();
		}
	}

	private void handleEdgeDrag(MouseEvent e) {
		Cursor cursor = window.getCursor();
		Point mouseLocation = e.getLocationOnScreen();
		int x = mouseLocation.x - listener.edgeStart.x;
		int y = mouseLocation.y - listener.edgeStart.y;

		int newWidth = listener.oldWidth;
		int newHeight = listener.oldHeight;
		int newLocX = listener.oldLocX;
		int newLocY = listener.oldLocY;

		switch (cursor.getType()) {
		case Cursor.S_RESIZE_CURSOR:
			newHeight = listener.oldHeight + y;
			break;
		case Cursor.E_RESIZE_CURSOR:
			newWidth = listener.oldWidth + x;
			break;
		case Cursor.SE_RESIZE_CURSOR:
			newWidth = listener.oldWidth + x;
			newHeight = listener.oldHeight + y;
			break;
		case Cursor.W_RESIZE_CURSOR:
			newWidth = listener.oldWidth - x;
			newLocX = listener.oldLocX + x;
			break;
		case Cursor.N_RESIZE_CURSOR:
			newHeight = listener.oldHeight - y;
			newLocY = listener.oldLocY + y;
			break;
		case Cursor.SW_RESIZE_CURSOR:
			newWidth = listener.oldWidth - x;
			newHeight = listener.oldHeight + y;
			newLocX = listener.oldLocX + x;
			break;
		case Cursor.NW_RESIZE_CURSOR:
			newWidth = listener.oldWidth - x;
			newHeight = listener.oldHeight - y;
			newLocX = listener.oldLocX + x;
			newLocY = listener.oldLocY + y;
			break;
		case Cursor.NE_RESIZE_CURSOR:
			newWidth = listener.oldWidth + x;
			newHeight = listener.oldHeight - y;
			newLocY = listener.oldLocY + y;
			break;
		}

		window.setSize(newWidth, newHeight);
		window.setLocation(newLocX, newLocY);
		window.width = newWidth;
		window.height = newHeight;

		listener.updateComponentLocationAndSize();
	}

	private void handleBoxDrag(MouseEvent e) {
		Box box = listener.boxController.getBoxById(listener.dragginBoxID);
		box.updateLocation(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateHoveredComponents(e);

		listener.propagateEvent(e, Box::mouseMoved);
	}

	

	private void updateHoveredComponents(MouseEvent e) {
		Set<UIComponent> oldHoveredComponents = new HashSet<>(hoveredComponents);
		Set<UIComponent> newHoveredComponents = new HashSet<>();

		for (UIComponent component : mainUI.getComponentList()) {
			if (component.isInRegion(e)) {
				newHoveredComponents.add(component);
				component.setHovered(true);
			} else {
				component.setHovered(false);
			}
		}

		if (!newHoveredComponents.equals(oldHoveredComponents)) {
			hoveredComponents.clear();
			hoveredComponents.addAll(newHoveredComponents);

			printHoveredComponents();
			listener.setCursor();

			if (isHoverStateChanged(listener.closeButton, hoveredComponents, oldHoveredComponents)) {
				listener.closeButton.repaint();
			}
			if (isHoverStateChanged(listener.maxButton, hoveredComponents, oldHoveredComponents)) {
				listener.maxButton.repaint();
			}
			if (isHoverStateChanged(listener.trayButton, hoveredComponents, oldHoveredComponents)) {
				listener.trayButton.repaint();
			}
			if (isHoverStateChanged(listener.fileButton, hoveredComponents, oldHoveredComponents)) {
				listener.fileButton.repaint();
				if (listener.fileButton.isHovered()) {
					listener.boxController.createBox();
				}
			}

			for (Box box : window.getMainUI().getBoxList()) {
				if (isHoverStateChanged(box, hoveredComponents, oldHoveredComponents))
					if (!hoveredComponents.contains(box))
						box.unhoverCloseButton();
			}

		}
	}

	private boolean isHoverStateChanged(UIComponent component, Set<UIComponent> hoveredComponents,
			Set<UIComponent> oldHoveredComponents) {
		boolean nowHovered = hoveredComponents.contains(component);
		boolean previouslyHovered = oldHoveredComponents.contains(component);
		return nowHovered != previouslyHovered;
	}

	private void printHoveredComponents() {
		System.out.println("Hovered component(s):");
		for (UIComponent component : hoveredComponents) {
			System.out.println("\t" + component);
		}
	}
}
