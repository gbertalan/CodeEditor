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
//	protected boolean adjustHeightOnResize = true;
//	protected boolean adjustWidthOnResize = true;

	public Component(Window window, int locX, int locY, int width, int height) {
		this.window = window;
		this.locX = locX;
		this.locY = locY;
		this.width = width;
		this.height = height;
//		window.addResizeListener(this);
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

//	@Override
//	public void onResize(int newWidth, int newHeight) {
//		if (adjustWidthOnResize) {
//			this.width = newWidth;
//		}
//
//		if (adjustHeightOnResize) {
//			this.height = newHeight;
//		}
//
//	}

	public void updateLocationAndSize(int locX, int locY, int width, int height) {
		this.locX = locX;
		this.locY = locY;
		this.width = width;
		this.height = height;
	}
	
	abstract public void updateLocX();
	
	public void updateLocY(int locY) {
		this.locY = locY;
	}
	
	public void updateWidth(int width) {
		this.width = width;
	}
	
	public void updateHeight(int height) {
		this.height = height;
	}

	@Override
	public abstract void draw(Graphics2D g2d);
}
