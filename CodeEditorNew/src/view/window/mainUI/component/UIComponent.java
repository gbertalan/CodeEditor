package view.window.mainUI.component;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import utils.ANSIText;
import view.window.Window;

public abstract class UIComponent implements VisualComponent, Comparable<UIComponent> {

	private static final int MIN_PRIORITY = 0;
	private static final int REPAINT_AREA_EXPANSION = 1;

	protected Window window;
	protected int drawPriority;

	protected int locX;
	protected int locY;
	protected int width;
	protected int height;

	protected boolean hovered;

	/**
	 * @param window
	 * @param drawPriority - highest priority (lowest value) is drawn first.
	 * @param locX
	 * @param locY
	 * @param width
	 * @param height
	 */
	public UIComponent(Window window, int drawPriority, int locX, int locY, int width, int height) {
		this.window = window;
		try {
			if (drawPriority >= MIN_PRIORITY) {
				this.drawPriority = drawPriority;
			} else {
				throw new Exception("Draw priority cannot be negative.");
			}
		} catch (Exception e) {
			System.out.println("Exception caught: " + e.getMessage());
		}
		this.locX = locX;
		this.locY = locY;
		this.width = width;
		this.height = height;
	}

	public int getDrawPriority() {
		return drawPriority;
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getComponentName() {
		return getClass().getSimpleName();
	}

	@Override
	public int compareTo(UIComponent other) {
		return Integer.compare(this.drawPriority, other.drawPriority);
	}

	/**
	 * Checks if a MouseEvent is within the bounds of the component with a given
	 * modifier. If it is, it sets the hovered field to true.
	 * 
	 * @param e        The MouseEvent to check.
	 * @param modifier The modifier to adjust the bounding box of the component.
	 */
	public boolean isHovered(MouseEvent e, int modifier) {
		hovered = e.getX() > locX + modifier && e.getY() > locY + modifier && e.getX() < locX + width - modifier
				&& e.getY() < locY + height - modifier ? true : false;
		return hovered;
	}

	public boolean isInRegion(MouseEvent e) {
		Rectangle rectangle = new Rectangle(locX, locY, width, height);
		return rectangle.contains(e.getPoint()) ? true : false;
	}

	public boolean getHovered() {
		return hovered;
	}

	public void setHovered(boolean isHovered) {
		hovered = isHovered;
	}

	@Override
	public abstract void draw(Graphics2D g2d);

	abstract public void update();

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [locX=" + locX + ", locY=" + locY + ", width=" + width + ", height="
				+ height + ", hovered=" + hovered + ", drawPriosity=" + drawPriority + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		UIComponent other = (UIComponent) obj;
		return locX == other.locX && locY == other.locY && width == other.width && height == other.height
				&& hovered == other.hovered && (window == null ? other.window == null : window.equals(other.window));
	}

	/**
	 * 
	 * @param secondaryCursor - to handle cases where the hovered components have
	 *                        the same cursor priority. e.g. the EdgeWest and
	 *                        EdgeNorth are both hovered,then the returned cursor is
	 *                        not W_RESIZE_CURSOR and not N_RESIZE_CURSOR, but
	 *                        NW_RESIZE_CURSOR.
	 * @return - the cursor that needs to be set when this component is hovered
	 */
	public abstract Cursor getCursor(int secondaryCursor);

	public Cursor getCursor() {
		return getCursor(Cursor.DEFAULT_CURSOR);
	}

	public void repaint() {
		System.out.println(ANSIText.purple("Repaint: " + toString()));
		Rectangle area = new Rectangle(locX - REPAINT_AREA_EXPANSION, locY - REPAINT_AREA_EXPANSION,
				width + (2 * REPAINT_AREA_EXPANSION), height + (2 * REPAINT_AREA_EXPANSION));
		window.getMainUI().repaint(area);
	}

	public void updateLocation(int x, int y) {
		locX = x;
		locY = y;
	}

}
