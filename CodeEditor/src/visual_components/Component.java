package visual_components;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import main.Window;

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
	 * modifier.
	 * 
	 * @param e        The MouseEvent to check.
	 * @param modifier The modifier to adjust the bounding box of the component.
	 */
	public boolean isHovered(MouseEvent e, int modifier) {
		hovered = e.getX() > locX + modifier && e.getY() > locY + modifier && e.getX() < locX + width - modifier
				&& e.getY() < locY + height - modifier ? true : false;
		System.out.println("hovered: " + this.getClass() + " " + hovered);
		return hovered;
	}

	public boolean getHovered() {
		return hovered;
	}

	abstract public void update();

	@Override
	public abstract void draw(Graphics2D g2d);
}
