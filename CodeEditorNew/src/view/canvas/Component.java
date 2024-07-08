package view.canvas;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import view.Window;

public abstract class Component implements VisualComponent {

    protected Window window;
    protected int locX;
    protected int locY;
    protected int width;
    protected int height;
    protected boolean hovered;

    public Component(Window window, int locX, int locY, int width, int height) {
        this.window = window;
        this.locX = locX;
        this.locY = locY;
        this.width = width;
        this.height = height;
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
        return getClass().getSimpleName() + " [locX=" + locX + ", locY=" + locY + ", width=" + width + ", height=" + height + ", hovered=" + hovered + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Component other = (Component) obj;
        return locX == other.locX && locY == other.locY && width == other.width && height == other.height 
               && hovered == other.hovered && (window == null ? other.window == null : window.equals(other.window));
    }
}
