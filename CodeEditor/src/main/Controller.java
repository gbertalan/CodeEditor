package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

import utils.Globals;
import visual_components.CloseButton;
import visual_components.Background;
import visual_components.TitleBar;

/**
 * The Controller class manages drawing components and handling input events.
 * 
 * @author Gergely Bertalan
 */
public class Controller extends JPanel {
	private static final long serialVersionUID = 1L;

	private Background roundedBackground;
	private TitleBar titleBar;
	private CloseButton closeButton;

	private Window window;

	private Point initialClick = new Point(0, 0);
	private Point edgeStart = new Point(0, 0);

	private int oldWidth;
	private int oldHeight;
	private int oldLocX;
	private int oldLocY;

	private boolean draggingByTitleBar;
	private boolean draggingByEdge;

	public Controller(Window window) {
		this.window = window;

		oldWidth = window.width;
		oldHeight = window.height;
		oldLocX = window.locX;
		oldLocY = window.locY;

		setBounds(0, 0, Window.jFrame.getWidth(), Window.jFrame.getHeight());
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);

		addMouseListener(new ControllerMouseListener());
		addMouseMotionListener(new ControllerMouseMotionListener());

		roundedBackground = new Background(window);
		titleBar = new TitleBar(window);
		closeButton = new CloseButton(window);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBounds(0, 0, Window.jFrame.getWidth(), Window.jFrame.getHeight());

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		roundedBackground.draw(g2d);
		titleBar.draw(g2d);
		closeButton.draw(g2d);

		g2d.dispose();
	}

	private class ControllerMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (titleBar.isHovered(e, window.frameThichness) && e.getClickCount() == 2) {
				if ((Window.jFrame.getExtendedState() & Window.MAXIMIZED_BOTH) == 0) {
					Window.jFrame.setExtendedState(Window.jFrame.getExtendedState() | Window.MAXIMIZED_BOTH);
					oldWidth = window.width;
					oldHeight = window.height;
					window.width = Window.jFrame.getWidth();
					window.height = Window.jFrame.getHeight();
				} else {
					Window.jFrame.setExtendedState(Window.jFrame.getExtendedState() & ~Window.MAXIMIZED_BOTH);
					window.width = oldWidth;
					window.height = oldHeight;
				}
				update();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (titleBar.isHovered(e, window.frameThichness)) {
				draggingByTitleBar = true;
				initialClick = e.getPoint();
			}

			// if not maximized:
			if ((Window.jFrame.getExtendedState() & Window.MAXIMIZED_BOTH) == 0) {
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
				window.width = Window.jFrame.getWidth();
				window.height = Window.jFrame.getHeight();
				window.locX = Window.jFrame.getLocation().x;
				window.locY = Window.jFrame.getLocation().y;
				oldWidth = Window.jFrame.getWidth();
				oldHeight = Window.jFrame.getHeight();
				oldLocX = Window.jFrame.getLocation().x;
				oldLocY = Window.jFrame.getLocation().y;
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
				if ((Window.jFrame.getExtendedState() & Window.MAXIMIZED_BOTH) == 0) {
					Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
					int x = mouseLocation.x - initialClick.x;
					int y = mouseLocation.y - initialClick.y;

					Window.jFrame.setLocation(x, y);
					window.locX = x;
					window.locY = y;
					oldLocX = x;
					oldLocY = y;

				} else { // if window is maximised, restore it:
					double ratio = e.getX() / (double) Window.jFrame.getWidth();
					Window.jFrame.setSize(window.width, window.height);
					initialClick = new Point((int) (window.width * ratio), e.getY());
				}

				update();
			}
			if (draggingByEdge) {
				Cursor cursor = getCursor();
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
				Window.jFrame.setSize(newWidth, newHeight);
				Window.jFrame.setLocation(newLocX, newLocY);

				window.width = newWidth;
				window.height = newHeight;

				update();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

			Window.Edge edge = window.getEdgeType(e.getPoint());
			Cursor cursor = edge.getPredefinedResizeCursor();

			int state = Window.jFrame.getExtendedState();
			if ((state & Window.MAXIMIZED_BOTH) != Window.MAXIMIZED_BOTH) {
				setCursor(cursor);
			} else {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	private void update() {
		updateComponents();
		Window.update();
	}

	private void updateComponents() {
		titleBar.update();
		roundedBackground.update();
		closeButton.update();
	}
}
