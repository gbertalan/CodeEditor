package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private Canvas canvas;

	public int width = 800;
	public int height = 600;

	public int locX = 100;
	public int locY = 100;

	public static JFrame jFrame;

	public int frameThichness = 6;

	public Window() {
		setTitle("Code Editor by Gergely Bertalan");
		try {
			Image icon = Toolkit.getDefaultToolkit().getImage("resources/logo.png");
			setIconImage(icon);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in loading icon image");
		}
		setSize(width, height);
		setLocation(locX, locY);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		getContentPane().setBackground(new Color(0, 0, 0, 0));
		getContentPane().setLayout(null);
		setVisible(true);

	}

	public void addCanvas(Canvas canvas) {
		if (getContentPane().getComponentCount() != 0) {
			getContentPane().removeAll();
		}
		this.canvas = canvas;
		getContentPane().add(canvas);
		revalidate();
		repaint();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Enum representing the directions (e.g., north, south-east) in which a window
	 * can be resized, each associated with a specific cursor type.
	 */
	public enum Edge {
		NW(Cursor.NW_RESIZE_CURSOR), N(Cursor.N_RESIZE_CURSOR), NE(Cursor.NE_RESIZE_CURSOR), E(Cursor.E_RESIZE_CURSOR),
		SE(Cursor.SE_RESIZE_CURSOR), S(Cursor.S_RESIZE_CURSOR), SW(Cursor.SW_RESIZE_CURSOR), W(Cursor.W_RESIZE_CURSOR),
		CENTER(Cursor.DEFAULT_CURSOR);

		private final int cursorType;

		Edge(int cursorType) {
			this.cursorType = cursorType;
		}

		/**
		 * Returns the appropriate resize cursor for the given window resize direction.
		 *
		 * @return the predefined cursor corresponding to the resize direction.
		 */
		public Cursor getPredefinedResizeCursor() {
			return Cursor.getPredefinedCursor(cursorType);
		}
	}

	/**
	 * This method checks the position of the point relative to the edges and
	 * corners of the window and returns the appropriate resize direction. E.g. if e
	 * is a Point at the bottom of the Window, S (South) from the Edge is returned.
	 *
	 * @param e the point to check, representing the location of a mouse event.
	 * @return the resize direction based on the position of the point, or null if
	 *         the point is outside the valid resize areas.
	 */
	public Edge getEdgeType(Point e) {
		if (e.x >= frameThichness && e.x <= getWidth() - frameThichness && e.y >= getHeight() - frameThichness)
			return Edge.S;
		else if (e.x >= getWidth() - frameThichness && e.y >= frameThichness && e.y <= getHeight() - frameThichness)
			return Edge.E;
		else if (e.x <= frameThichness && e.y >= frameThichness && e.y <= getHeight() - frameThichness)
			return Edge.W;
		else if (e.x >= frameThichness && e.x <= getWidth() - frameThichness && e.y <= frameThichness)
			return Edge.N;
		else if (e.x <= frameThichness && e.y <= frameThichness)
			return Edge.NW;
		else if (e.x >= getWidth() - frameThichness && e.y <= frameThichness)
			return Edge.NE;
		else if (e.x >= getWidth() - frameThichness && e.y >= getHeight() - frameThichness)
			return Edge.SE;
		else if (e.x <= frameThichness && e.y >= getHeight() - frameThichness)
			return Edge.SW;
		else if (e.x > frameThichness && e.x < getWidth() - frameThichness && e.y > frameThichness
				&& e.y < getHeight() - frameThichness)
			return Edge.CENTER;
		else {
			return null;
		}
	}

	public boolean isEdgeHovered(MouseEvent e) {
		if (e.getX() < frameThichness || e.getY() < frameThichness || e.getX() > width - frameThichness
				|| e.getY() > height - frameThichness)
			return true;
		else
			return false;
	}

	public boolean isMaximized() {
		int state = this.getExtendedState();
		int maximized = Window.MAXIMIZED_BOTH;
		if ((state & maximized) == maximized) {
			return true;
		} else {
			return false;
		}
	}

}
