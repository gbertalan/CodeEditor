package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;

import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class BoxOutput extends UIComponent {

    private static int ARROW_WIDTH = 30;

    private UIComponent arrow;
    private int initialArrowOffsetX;
    private int initialArrowOffsetY;

    public BoxOutput(Window window, int drawPriority, int locX, int locY, int width, int height) {
        super(window, drawPriority, locX, locY, width, height);
        arrow = new BoxArrow(window, drawPriority, locX + 1, locY, ARROW_WIDTH, height);
        initialArrowOffsetX = arrow.getLocX() - locX;
        initialArrowOffsetY = arrow.getLocY() - locY;
        window.getMainUI().addComponent(arrow);
    }

    public UIComponent getArrow() {
        return arrow;
    }

    public void updateArrowLocation(int boxLocX, int boxLocY) {
        arrow.updateLocation(boxLocX + initialArrowOffsetX, boxLocY + initialArrowOffsetY);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO Auto-generated method stub
        // g2d.setColor(Color.BLUE);
        // g2d.fillRect(locX, locY, width, height);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public Cursor getCursor(int secondaryCursor) {
        // TODO Auto-generated method stub
        return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }
}
