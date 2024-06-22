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

import view.Window;

public class Listener extends MouseAdapter {
	private Window window;

	private Point initialClick = new Point(0, 0);
	private Point edgeStart = new Point(0, 0);

	private int oldWidth;
	private int oldHeight;
	private int oldLocX;
	private int oldLocY;

	private boolean draggingByTitleBar;
	private boolean draggingByEdge;

	private int previousState = JFrame.NORMAL;

	public Listener(Window window) {
		this.window = window;

		oldWidth = window.width;
		oldHeight = window.height;
		oldLocX = window.locX;
		oldLocY = window.locY;

		window.addMouseListener(new ControllerMouseListener());
		window.addMouseMotionListener(new ControllerMouseMotionListener());
		window.addWindowStateListener(new ControllerWindowStateListener());
	}

	private class ControllerMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (window.getCanvas().getTitleBar().isHovered(e, window.frameThichness) && e.getClickCount() == 2) {
				if ((window.getExtendedState() & Window.MAXIMIZED_BOTH) == 0) {
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
			if (window.getCanvas().getCloseButton().isHovered(e, 0)) {
				System.exit(0);
			} else if (window.getCanvas().getMaxButton().isHovered(e, 0)) {
				if ((window.getExtendedState() & Window.MAXIMIZED_BOTH) == 0) {
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
			} else if (window.getCanvas().getTrayButton().isHovered(e, 0)) {
				window.setExtendedState(JFrame.ICONIFIED);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (window.getCanvas().getTitleBar().isHovered(e, window.frameThichness)) {
				draggingByTitleBar = true;
				initialClick = e.getPoint();
			}

			// if not maximized:
			if ((window.getExtendedState() & Window.MAXIMIZED_BOTH) == 0) {
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

	private class ControllerMouseMotionListener extends MouseMotionAdapter {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (draggingByTitleBar) {
				window.width = oldWidth;
				window.height = oldHeight;
				window.locX = oldLocX;
				window.locY = oldLocY;

				// if window is not maximised, drag it:
				if ((window.getExtendedState() & Window.MAXIMIZED_BOTH) == 0) {
					Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
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
				Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
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

			Window.Edge edge = window.getEdgeType(e.getPoint());
			Cursor cursor = edge.getPredefinedResizeCursor();

			int state = window.getExtendedState();
			if ((state & Window.MAXIMIZED_BOTH) != Window.MAXIMIZED_BOTH) {
				window.setCursor(cursor);
			} else {
				window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			window.getCanvas().getCloseButton().isHovered(e, 0);
			window.getCanvas().getTrayButton().isHovered(e, 0);
			window.getCanvas().getMaxButton().isHovered(e, 0);

		}
	}

	private class ControllerWindowStateListener implements WindowStateListener {
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
		window.getCanvas().getTitleBar().update();
		window.getCanvas().getCanvasBackground().update();
		window.getCanvas().getCloseButton().update();
		window.getCanvas().getTrayButton().update();
		window.getCanvas().getMaxButton().update();

	}
}
