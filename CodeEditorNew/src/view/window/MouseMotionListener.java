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
		// TODO Auto-generated method stub
		String keyWord = "Box" + Integer.toString(listener.dragginBoxID);
		Box box = (Box) mainUI.getComponent(keyWord);
		box.updateLocation(e.getX(), e.getY());
		System.out.println(ANSIText.bold("boxdrag"));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateHoveredComponents(e);
		
		for (Box box : window.getMainUI().getBoxList()) {
			if(hoveredComponents.contains(box)) {
				box.mouseMoved(e);
			}
		}
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
//            mainUI.update();

			printHoveredComponents();
			listener.setCursor();

			if (isHoverStateChanged(mainUI.getComponent("CloseButton"), hoveredComponents, oldHoveredComponents)) {
				mainUI.getComponent("CloseButton").repaint();
			}
			if (isHoverStateChanged(mainUI.getComponent("MaxButton"), hoveredComponents, oldHoveredComponents)) {
				mainUI.getComponent("MaxButton").repaint();
			}
			if (isHoverStateChanged(mainUI.getComponent("TrayButton"), hoveredComponents, oldHoveredComponents)) {
				mainUI.getComponent("TrayButton").repaint();
			}
			if (isHoverStateChanged(mainUI.getComponent("FileButton"), hoveredComponents, oldHoveredComponents)) {
				mainUI.getComponent("FileButton").repaint();
				if (mainUI.getComponent("FileButton").isHovered()) {
					Box newBox = new Box(window, 1, 180, 100);
					window.getMainUI().addComponent(newBox);
				}
			}

		}
	}

	private boolean isHoverStateChanged(UIComponent component, Set<UIComponent> hoveredComponents,
			Set<UIComponent> oldHoveredComponents) {
		boolean nowHovered = hoveredComponents.contains(component);
		boolean previouslyHovered = oldHoveredComponents.contains(component);

		boolean hoverStateChanged = nowHovered != previouslyHovered;

		return hoverStateChanged;
	}

	private void printHoveredComponents() {
		System.out.println("Hovered component(s):");
		for (UIComponent component : hoveredComponents) {
			System.out.println("\t" + component);
		}
	}
}
