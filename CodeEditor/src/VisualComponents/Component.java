package VisualComponents;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import main.Window;

public abstract class Component implements VisualComponent, ResizeListener {

    protected int locX;
    protected int locY;
    protected int width;
    protected int height;
    protected boolean adjustHeightOnResize;

    public Component(Window window, int locX, int locY, int width, int height) {
        this.locX = 0;
        this.locY = 0;
        this.width = width;
        this.height = height;
        window.addResizeListener(this);
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
        return e.getX() >= locX && e.getY() >= locY && e.getX() < locX + width && e.getY() <= locY + height;
    }

    @Override
    public void onResize(int newWidth, int newHeight) {
        this.width = newWidth;

        if (adjustHeightOnResize) {
            this.height = newHeight;
        }
        
    }

    @Override
    public abstract void draw(Graphics2D g2d);
}
