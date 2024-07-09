package view.window;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JFrame;

import utils.ANSIText;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;

/**
 * WindowListener is responsible for handling events related to the window's
 * control elements:
 * 
 * 1. resize window by dragging edge; 2. titlebar events; 3. window button
 * (minimize, maximize, close) events
 */

public class Listener extends MouseAdapter {
	private Window window;

	private Point initialClick = new Point(0, 0);
	public static Point mouseMoved = new Point(0, 0);
	public static Point mouseClicked = new Point(0, 0);
	public static Point mouseDragged = new Point(0, 0);
	public static Point mouseReleased = new Point(0, 0);
	public static boolean mouseExited;

	private Point edgeStart = new Point(0, 0);

	private int oldWidth;
	private int oldHeight;
	private int oldLocX;
	private int oldLocY;

	private boolean draggingByTitleBar;
	private boolean draggingByEdge;

	private MainUI mainUI;
	private UIComponent titleBar, closeButton, trayButton, maxButton, sidePanelLeft, sidePanelRight, fileButton, footer,
			edgeWest, edgeNorth, edgeEast, edgeSouth;

	private HashSet<UIComponent> hoveredComponents = new HashSet<>();

	public Listener(Window window) {
		System.out.println(ANSIText.red("MainUIListener constructor is called."));

		this.window = window;
		this.mainUI = window.getMainUI();

		initializeComponents();

		oldWidth = window.width;
		oldHeight = window.height;
		oldLocX = window.locX;
		oldLocY = window.locY;
	}

	private void initializeComponents() {
		edgeWest = mainUI.getComponent("EdgeWest");
		edgeNorth = mainUI.getComponent("EdgeNorth");
		edgeEast = mainUI.getComponent("EdgeEast");
		edgeSouth = mainUI.getComponent("EdgeSouth");
		titleBar = mainUI.getComponent("TitleBar");
		closeButton = mainUI.getComponent("CloseButton");
		trayButton = mainUI.getComponent("TrayButton");
		maxButton = mainUI.getComponent("MaxButton");
		sidePanelLeft = mainUI.getComponent("SidePanelLeft");
		sidePanelRight = mainUI.getComponent("SidePanelRight");
		fileButton = mainUI.getComponent("FileButton");
		footer = mainUI.getComponent("Footer");

	}

	public class MouseListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {

			mouseExited = false;
		}

		@Override
		public void mouseExited(MouseEvent e) {

			mouseExited = true;

			unhoverAllComponents();
			mainUI.update();
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			mouseClicked = e.getPoint();

			if ((hoveredComponents.contains(titleBar) && e.getClickCount() == 2)
					|| hoveredComponents.contains(maxButton)) {
				if (!window.isMaximized()) {
					window.setMaximized(true);
					oldWidth = window.width;
					oldHeight = window.height;
					window.width = window.getWidth();
					window.height = window.getHeight();
				} else {
					window.setMaximized(false);
					window.width = oldWidth;
					window.height = oldHeight;
				}
				updateComponentLocationAndSize();
			}
			if (hoveredComponents.contains(closeButton)) {
				WindowEvent windowClosing = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosing);
			} else if (hoveredComponents.contains(trayButton)) {
				window.setExtendedState(JFrame.ICONIFIED);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (hoveredComponents.contains(titleBar) && !hoveredComponents.contains(trayButton)
					&& !hoveredComponents.contains(maxButton) && !hoveredComponents.contains(closeButton)
					&& !hoveredComponents.contains(edgeWest) && !hoveredComponents.contains(edgeNorth)
					&& !hoveredComponents.contains(edgeEast) && !hoveredComponents.contains(edgeSouth)) {
				draggingByTitleBar = true;
				initialClick = e.getPoint();
			}

			// if not maximized:
			if (!window.isMaximized()) {
				if (hoveredComponents.contains(edgeWest) || hoveredComponents.contains(edgeNorth)
						|| hoveredComponents.contains(edgeEast) || hoveredComponents.contains(edgeSouth)) {
					draggingByEdge = true;
					edgeStart = e.getLocationOnScreen();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mouseReleased = e.getPoint();

			if (draggingByTitleBar)
				draggingByTitleBar = false;

			if (draggingByEdge) {
				window.width = window.getWidth();
				window.height = window.getHeight();
				window.locX = window.getLocation().x;
				window.locY = window.getLocation().y;
				oldWidth = window.getWidth();
				oldHeight = window.getHeight();
				oldLocX = window.getLocation().x;
				oldLocY = window.getLocation().y;
				draggingByEdge = false;
			}

			window.getInnerCanvas().mouseReleased();
		}
	}

	public class MouseMotionListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {

			mouseDragged = e.getPoint();

			if (draggingByTitleBar) {
				window.width = oldWidth;
				window.height = oldHeight;
				window.locX = oldLocX;
				window.locY = oldLocY;

				// if window is not maximised, drag it:
				if (!window.isMaximized()) {
					Point mouseLocation = e.getLocationOnScreen();
					int x = mouseLocation.x - initialClick.x;
					int y = mouseLocation.y - initialClick.y;

					window.setLocation(x, y);
					window.locX = x;
					window.locY = y;
					oldLocX = x;
					oldLocY = y;

				} else { // if window is maximised, restore it:
					double ratio = e.getX() / (double) window.getWidth();
					window.setSize(window.width, window.height);
					initialClick = new Point((int) (window.width * ratio), e.getY());
					updateComponentLocationAndSize();
				}

			}

			else if (draggingByEdge) {
				Cursor cursor = window.getCursor();
				Point mouseLocation = e.getLocationOnScreen();
				int x = mouseLocation.x - edgeStart.x;
				int y = mouseLocation.y - edgeStart.y;

				int newWidth = oldWidth;
				int newHeight = oldHeight;
				int newLocX = oldLocX;
				int newLocY = oldLocY;

				switch (cursor.getType()) {
				case Cursor.S_RESIZE_CURSOR:
					newWidth = oldWidth;
					newHeight = oldHeight + y;
					newLocX = oldLocX;
					newLocY = oldLocY;
					break;
				case Cursor.E_RESIZE_CURSOR:
					newWidth = oldWidth + x;
					newHeight = oldHeight;
					newLocX = oldLocX;
					newLocY = oldLocY;
					break;
				case Cursor.SE_RESIZE_CURSOR:
					newWidth = oldWidth + x;
					newHeight = oldHeight + y;
					newLocX = oldLocX;
					newLocY = oldLocY;
					break;
				case Cursor.W_RESIZE_CURSOR:
					newWidth = oldWidth - x;
					newHeight = oldHeight;
					newLocX = oldLocX + x;
					newLocY = oldLocY;
					break;
				case Cursor.N_RESIZE_CURSOR:
					newWidth = oldWidth;
					newHeight = oldHeight - y;
					newLocX = oldLocX;
					newLocY = oldLocY + y;
					break;
				case Cursor.SW_RESIZE_CURSOR:
					newWidth = oldWidth - x;
					newHeight = oldHeight + y;
					newLocX = oldLocX + x;
					newLocY = oldLocY;
					break;
				case Cursor.NW_RESIZE_CURSOR:
					newWidth = oldWidth - x;
					newHeight = oldHeight - y;
					newLocX = oldLocX + x;
					newLocY = oldLocY + y;
					break;
				case Cursor.NE_RESIZE_CURSOR:
					newWidth = oldWidth + x;
					newHeight = oldHeight - y;
					newLocX = oldLocX;
					newLocY = oldLocY + y;
					break;
				default:
					break;
				}
				window.setSize(newWidth, newHeight);
				window.setLocation(newLocX, newLocY);

				window.width = newWidth;
				window.height = newHeight;

				updateComponentLocationAndSize();
			} else if (!closeButton.getHovered() && !trayButton.getHovered() && !maxButton.getHovered()
					&& !fileButton.getHovered() && !sidePanelLeft.getHovered()) {
				window.getInnerCanvas().mouseDragged();
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {

			mouseMoved = e.getPoint();

			updateHoveredComponents(e);
		}

	}

	public class StateListener implements WindowStateListener {

		private int previousState = JFrame.NORMAL;

		@Override
		public void windowStateChanged(WindowEvent e) {
			// If the window is being minimized, save its current state
			if (e.getNewState() == JFrame.ICONIFIED) {
				previousState = e.getOldState();
			}

			// If the window is being restored from minimized state, restore it to its
			// previous state
			if (e.getOldState() == JFrame.ICONIFIED
					&& (e.getNewState() == JFrame.NORMAL || e.getNewState() == JFrame.MAXIMIZED_BOTH)) {
				window.setExtendedState(previousState);
			}
		}

	}

	private void updateComponentLocationAndSize() {
		allUIComponent(UIComponent -> UIComponent.update());
	}

	private void updateHoveredComponents(MouseEvent e) {
		Set<UIComponent> oldHoveredComponents = new HashSet<>(hoveredComponents);
		Set<UIComponent> newHoveredComponents = new HashSet<>();

		for (UIComponent component : mainUI.componentList) {
//			UIComponent component = entry.getValue();
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
			mainUI.update();
			printHoveredComponents();
			setCursor();
		}
	}

	private void printHoveredComponents() {
		System.out.println("Hovered component(s):");
		for (UIComponent component : hoveredComponents) {
			System.out.println("\t" + component.toString());
		}
	}

	private void unhoverAllComponents() {
		for (UIComponent component : hoveredComponents) {
			component.setHovered(false);
		}
		hoveredComponents.clear();
	}

	private void setCursor() {

		if (hoveredComponents.contains(edgeSouth)) {
			if (hoveredComponents.contains(edgeWest)) {
				window.setCursor(edgeSouth.getCursor(Cursor.W_RESIZE_CURSOR));
			} else if (hoveredComponents.contains(edgeEast)) {
				window.setCursor(edgeSouth.getCursor(Cursor.E_RESIZE_CURSOR));
			} else {
				window.setCursor(edgeSouth.getCursor());
			}
		} else if (hoveredComponents.contains(edgeNorth)) {
			if (hoveredComponents.contains(edgeWest)) {
				window.setCursor(edgeNorth.getCursor(Cursor.W_RESIZE_CURSOR));
			} else if (hoveredComponents.contains(edgeEast)) {
				window.setCursor(edgeNorth.getCursor(Cursor.E_RESIZE_CURSOR));
			} else {
				window.setCursor(edgeNorth.getCursor());
			}
		} else {
			Cursor highestPriorityCursor = null;
			for (UIComponent component : hoveredComponents) {
				Cursor cursor = component.getCursor();
				if (highestPriorityCursor == null || isHigherPriority(cursor, highestPriorityCursor)) {
					highestPriorityCursor = cursor;
				} else if (!isHigherPriority(highestPriorityCursor, cursor)) {
				}
			}

			if (highestPriorityCursor != null) {
				window.setCursor(highestPriorityCursor);
			} else {
				window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}

	private boolean isHigherPriority(Cursor cursor1, Cursor cursor2) {
		int priority1 = getCursorPriority(cursor1);
		int priority2 = getCursorPriority(cursor2);
		return priority1 < priority2;
	}

	private int getCursorPriority(Cursor cursor) {
		// Lower number indicates higher priority
		switch (cursor.getType()) {
		case Cursor.N_RESIZE_CURSOR:
		case Cursor.S_RESIZE_CURSOR:
		case Cursor.W_RESIZE_CURSOR:
		case Cursor.E_RESIZE_CURSOR:
		case Cursor.NE_RESIZE_CURSOR:
		case Cursor.NW_RESIZE_CURSOR:
		case Cursor.SE_RESIZE_CURSOR:
		case Cursor.SW_RESIZE_CURSOR:
			return 1;
		case Cursor.HAND_CURSOR:
			return 2;
		case Cursor.MOVE_CURSOR:
			return 3;
		case Cursor.TEXT_CURSOR:
			return 4;
		case Cursor.WAIT_CURSOR:
			return 5;
		case Cursor.CROSSHAIR_CURSOR:
			return 6;
		case Cursor.DEFAULT_CURSOR:
			return 7;
		default:
			return Integer.MAX_VALUE; // Lowest priority
		}
	}

	/**
	 * Apply an action to all the UIComponents. Actions are the methods of the
	 * UIComponent class.
	 * 
	 * @param action
	 */
	private void allUIComponent(Consumer<UIComponent> action) {
		for (UIComponent component : mainUI.componentList) {
			action.accept(component);
		}
	}
}
