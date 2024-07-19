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
    protected int locX, locY, width, height;
    protected boolean hovered;
    protected double doubleLocX, doubleLocY, doubleWidth, doubleHeight;

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
        if (drawPriority < MIN_PRIORITY) {
            throw new IllegalArgumentException("Draw priority cannot be negative.");
        }
        this.drawPriority = drawPriority;
        this.locX = locX;
        this.locY = locY;
        this.width = width;
        this.height = height;
        this.doubleLocX = locX;
        this.doubleLocY = locY;
        this.doubleWidth = width;
        this.doubleHeight = height;
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
        hovered = new Rectangle(locX + modifier, locY + modifier, width - 2 * modifier, height - 2 * modifier).contains(e.getPoint());
        return hovered;
    }

    public boolean isInRegion(MouseEvent e) {
        return new Rectangle(locX, locY, width, height).contains(e.getPoint());
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean isHovered) {
        this.hovered = isHovered;
    }

    @Override
    public abstract void draw(Graphics2D g2d);

    public abstract void update();

    @Override
    public String toString() {
        return String.format("%s [locX=%d, locY=%d, width=%d, height=%d, hovered=%b, drawPriority=%d]", 
            getClass().getSimpleName(), locX, locY, width, height, hovered, drawPriority);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UIComponent other = (UIComponent) obj;
        return locX == other.locX && locY == other.locY && width == other.width && height == other.height
                && hovered == other.hovered && window.equals(other.window);
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
        System.out.println(ANSIText.purple("UIComponent Repaint: " + toString()));
        Rectangle area = new Rectangle(locX - REPAINT_AREA_EXPANSION, locY - REPAINT_AREA_EXPANSION,
                width + 2 * REPAINT_AREA_EXPANSION, height + 2 * REPAINT_AREA_EXPANSION);
        window.getMainUI().repaint(area);
    }

    public void updateLocation(int x, int y) {
        setLocation(x, y);
        setDoubleLocation(x, y);
    }

    public void setDoubleLocation(double doubleLocX, double doubleLocY) {
        this.doubleLocX = doubleLocX;
        this.doubleLocY = doubleLocY;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setLocation(int x, int y) {
        this.locX = x;
        this.locY = y;
    }

    public void setDoubleSize(double width, double height) {
        this.doubleWidth = width;
        this.doubleHeight = height;
    }

    public double getDoubleLocX() {
        return doubleLocX;
    }

    public double getDoubleLocY() {
        return doubleLocY;
    }

    public double getDoubleWidth() {
        return doubleWidth;
    }

    public double getDoubleHeight() {
        return doubleHeight;
    }

}
