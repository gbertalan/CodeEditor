package control;

import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import view.Component;
import view.FileButton;
import view.SidePanelLeft;
import view.Window;

/**
 * WindowListener is responsible for handling events related to the window's
 * control elements:
 * 
 * 1. resize window by dragging edge; 2. titlebar events; 3. window button
 * (minimize, maximize, close) events
 */

public class WindowListener extends MouseAdapter {
	private Window window;

	private Point initialClick = new Point(0, 0);
	private Point edgeStart = new Point(0, 0);

	private int oldWidth;
	private int oldHeight;
	private int oldLocX;
	private int oldLocY;

	private boolean draggingByTitleBar;
	private boolean draggingByEdge;

	private Component titleBar, closeButton, trayButton, maxButton, sidePanelLeft, sidePanelRight, fileButton, settingsButton, footer;

	public WindowListener(Window window) {
		this.window = window;

		titleBar = window.getCanvas().getTitleBar();
		closeButton = window.getCanvas().getCloseButton();
		trayButton = window.getCanvas().getTrayButton();
		maxButton = window.getCanvas().getMaxButton();
		sidePanelLeft = window.getCanvas().getSidePanelLeft();
		sidePanelRight = window.getCanvas().getSidePanelRight();
		fileButton = window.getCanvas().getFileButton();
		settingsButton = window.getCanvas().getSettingsButton();
		footer = window.getCanvas().getFooter();

		oldWidth = window.width;
		oldHeight = window.height;
		oldLocX = window.locX;
		oldLocY = window.locY;

		window.addMouseListener(new MouseListener());
		window.addMouseMotionListener(new MouseMotionListener());
		window.addWindowStateListener(new StateListener());
	}

	private class MouseListener extends MouseAdapter {

		@Override
		public void mouseExited(MouseEvent e) {
			titleBar.setHovered(false);
			closeButton.setHovered(false);
			trayButton.setHovered(false);
			maxButton.setHovered(false);
			fileButton.setHovered(false);
			settingsButton.setHovered(false);
			window.getCanvas().update();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
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
				updateComponents();
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
		}
	}

	private class MouseMotionListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {

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

				updateComponents();
			}

			if (draggingByEdge) {
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

				updateComponents();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// set resize cursor when pointing at edge of frame:

			Cursor currentCursor = window.getCursor();
			if (!window.isMaximized()) {
				Window.Edge edge = window.getEdgeType(e.getPoint());
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
			settingsButton.isHovered(e, 0);
			window.getCanvas().update();

		}

	}

	private class StateListener implements WindowStateListener {

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

	private void updateComponents() {
		titleBar.update();
		closeButton.update();
		trayButton.update();
		maxButton.update();
		sidePanelLeft.update();
		sidePanelRight.update();
		footer.update();
	}
}
