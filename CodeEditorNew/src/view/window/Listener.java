package view.window;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Map.Entry;

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

	private UIComponent titleBar, closeButton, trayButton, maxButton, sidePanelLeft, sidePanelRight, fileButton, footer;

	private ArrayList<UIComponent> hoveredComponents = new ArrayList<>();

	public Listener(Window window) {
		System.out.println(ANSIText.red("MainUIListener constructor is called."));
		
		this.window = window;

		initializeComponents();

		oldWidth = window.width;
		oldHeight = window.height;
		oldLocX = window.locX;
		oldLocY = window.locY;

//		window.addMouseListener(new MouseListener());
//		window.addMouseMotionListener(new MouseMotionListener());
//		window.addWindowStateListener(new StateListener());
	}

	private void initializeComponents() {
		MainUI mainUI = window.getMainUI();
		titleBar = mainUI.getComponent("titleBar");
		closeButton = mainUI.getComponent("closeButton");
		trayButton = mainUI.getComponent("trayButton");
		maxButton = mainUI.getComponent("maxButton");
		sidePanelLeft = mainUI.getComponent("sidePanelLeft");
		sidePanelRight = mainUI.getComponent("sidePanelRight");
		fileButton = mainUI.getComponent("fileButton");
		footer = mainUI.getComponent("footer");
	}

	public class MouseListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {

			mouseExited = false;
		}

		@Override
		public void mouseExited(MouseEvent e) {

			mouseExited = true;

			unhoverAll();
			hoveredComponents = null;
			window.getMainUI().update();
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			mouseClicked = e.getPoint();

			if ((titleBar.isHovered(e, window.frameThichness) && e.getClickCount() == 2) || maxButton.isHovered(e, 0)) {
				if (!window.isMaximized()) {
					window.setExtendedState(window.getExtendedState() | Window.MAXIMIZED_BOTH);
					oldWidth = window.width;
					oldHeight = window.height;
					window.width = window.getWidth();
					window.height = window.getHeight();
				} else {
					window.setExtendedState(window.getExtendedState() & ~Window.MAXIMIZED_BOTH);
					window.width = oldWidth;
					window.height = oldHeight;
				}
				updateComponentLocationAndSize();
			}
			if (closeButton.isHovered(e, 0)) {
				System.exit(0);
			} else if (trayButton.isHovered(e, 0)) {
				window.setExtendedState(JFrame.ICONIFIED);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (titleBar.isHovered(e, window.frameThichness) && !trayButton.getHovered() && !maxButton.getHovered()
					&& !closeButton.getHovered()) {
				draggingByTitleBar = true;
				initialClick = e.getPoint();
			}

			// if not maximized:
			if (!window.isMaximized()) {
				Window.Edge edge = window.getEdgeType(e.getPoint());
				if (edge != Window.Edge.CENTER) {
					draggingByEdge = true;
					edgeStart = e.getLocationOnScreen();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mouseReleased = e.getPoint();

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
//					Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
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
				}

				updateComponentLocationAndSize();
			}

			else if (draggingByEdge) {
				Cursor cursor = window.getCursor();
//				Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
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

			window.getMainUI().update();

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// set resize cursor when pointing at edge of frame:

			mouseMoved = e.getPoint();

			updateHoveredComponents(e);
			printHoveredComponents();

			Cursor currentCursor = window.getCursor();
			if (!window.isMaximized()) {
				Window.Edge edge = window.getEdgeType(mouseMoved);
				Cursor cursor = edge.getPredefinedResizeCursor();
				if (currentCursor != cursor) {
					window.setCursor(cursor);
				}
			} else {
				Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
				if (currentCursor != defaultCursor) {
					window.setCursor(defaultCursor);
				}
			}

			// update button hovered state:
			closeButton.isHovered(e, 0);
			trayButton.isHovered(e, 0);
			maxButton.isHovered(e, 0);
			fileButton.isHovered(e, 0);
			sidePanelLeft.isHovered(e, 0);

			window.getMainUI().update();

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

		titleBar.update();
		closeButton.update();
		trayButton.update();
		maxButton.update();
		sidePanelLeft.update();
		sidePanelRight.update();
		footer.update();
	}

	public void updateHoveredComponents(MouseEvent e) {
		ArrayList<UIComponent> hovered = new ArrayList<>();
		for (Entry<String, UIComponent> entry : window.getMainUI().componentMap.entrySet()) {
			UIComponent component = entry.getValue();
			if (component.isInRegion(e)) {
				hovered.add(component);
			}
		}
		if (!hovered.equals(hoveredComponents))
			hoveredComponents = hovered;
	}

	public void printHoveredComponents() {
		System.out.println("Hovered component(s):");
		for (UIComponent component : hoveredComponents) {

			System.out.println("\t" + component.toString());
		}
	}

	private void unhoverAll() {
		titleBar.setHovered(false);
		closeButton.setHovered(false);
		trayButton.setHovered(false);
		maxButton.setHovered(false);
		fileButton.setHovered(false);
	}
}
