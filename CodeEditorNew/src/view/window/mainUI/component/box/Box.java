package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;

import view.window.MouseWheelListener;
import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class Box extends UIComponent {

    public static int boxCounter;

    private static final int WIDTH = 280;
    private static final int HEIGHT = 640;

    private int mouseOffsetX;
    private int mouseOffsetY;
    
    private BoxHeader boxHeader = new BoxHeader();

    public Box(Window window, int drawPriority, int locX, int locY) {
        super(window, drawPriority, locX, locY, WIDTH, HEIGHT);
        repaint();
    }

    @Override
    public void draw(Graphics2D g2d) {
    	
    	g2d.setColor(Color.GRAY);
        g2d.fillRect(locX, locY, width, height);
        
        boxHeader.draw(g2d, locX, locY, width, height);
        
        g2d.setColor(Color.WHITE);
        g2d.drawRect(locX, locY, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void updateLocation(int x, int y) {
        repaint();
        int newX = x - mouseOffsetX;
        int newY = y - mouseOffsetY;
        setLocation(newX, newY);
        setDoubleLocation(newX, newY);
        repaint();
    }

    @Override
    public Cursor getCursor(int secondaryCursor) {
        return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

    public void setMouseOffset(int x, int y) {
        this.mouseOffsetX = x;
        this.mouseOffsetY = y;
    }

    public void zoom(Point mouseLocation) {
        repaint();
        adjustComponent(this, mouseLocation);
        repaint();
    }

    private void adjustComponent(UIComponent component, Point mouseLocation) {
        component.setLocation((int) Math.round(calcX(component, mouseLocation.x)),
                              (int) Math.round(calcY(component, mouseLocation.y)));
        component.setDoubleLocation(calcX(component, mouseLocation.x), calcY(component, mouseLocation.y));
        component.setSize((int) Math.round(calcWidth(component)), (int) Math.round(calcHeight(component)));
        component.setDoubleSize(calcWidth(component), calcHeight(component));
    }

    private double calcX(UIComponent component, int mouseX) {
        double zoom = MouseWheelListener.zoomValue;
        double componentX = component.getDoubleLocX();
        return mouseX + ((componentX - mouseX) * zoom);
    }

    private double calcY(UIComponent component, int mouseY) {
        double zoom = MouseWheelListener.zoomValue;
        double componentY = component.getDoubleLocY();
        return mouseY + ((componentY - mouseY) * zoom);
    }

    private double calcWidth(UIComponent component) {
        return component.getDoubleWidth() * MouseWheelListener.zoomValue;
    }

    private double calcHeight(UIComponent component) {
        return component.getDoubleHeight() * MouseWheelListener.zoomValue;
    }
}
