package VisualComponents;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import main.Window;

public abstract class Component implements VisualComponent {

	protected Window window;
	protected int locX;
	protected int locY;
	protected int width;
	protected int height;

	public Component(Window window, int locX, int locY, int width, int height) {
		this.window = window;
		this.locX = locX;
		this.locY = locY;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getLocX() {
		return locX;
	}

	@Override
	public int getLocY() {
		return locY;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public boolean isHovered(MouseEvent e) {
		return e.getX() >= locX + window.frameThichness && e.getY() >= locY + window.frameThichness
				&& e.getX() < locX + width - window.frameThichness && e.getY() <= locY + height - window.frameThichness;
	}
	
	abstract public void update();

	@Override
	public abstract void draw(Graphics2D g2d);
}
