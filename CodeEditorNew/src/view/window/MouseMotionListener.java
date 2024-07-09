package view.window;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import view.window.Window;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;

import java.util.HashSet;
import java.util.Set;

public class MouseMotionListener extends MouseMotionAdapter {
    private Listener listener;
    private Window window;
    private MainUI mainUI;
    private HashSet<UIComponent> hoveredComponents;

    public MouseMotionListener(Listener listener) {
        this.listener = listener;
        this.window = listener.window;
        this.mainUI = listener.mainUI;
        this.hoveredComponents = listener.hoveredComponents;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (listener.draggingByTitleBar) {
            window.width = listener.oldWidth;
            window.height = listener.oldHeight;
            window.locX = listener.oldLocX;
            window.locY = listener.oldLocY;

            if (!window.isMaximized()) {
                Point mouseLocation = e.getLocationOnScreen();
                int x = mouseLocation.x - listener.initialPressOnTitleBar.x;
                int y = mouseLocation.y - listener.initialPressOnTitleBar.y;

                window.setLocation(x, y);
                window.locX = x;
                window.locY = y;
                listener.oldLocX = x;
                listener.oldLocY = y;
            } else {
                double ratio = e.getX() / (double) window.getWidth();
                window.setSize(window.width, window.height);
                listener.initialPressOnTitleBar = new Point((int) (window.width * ratio), e.getY());
                listener.updateComponentLocationAndSize();
            }
        } else if (listener.draggingByEdge) {
            Cursor cursor = window.getCursor();
            Point mouseLocation = e.getLocationOnScreen();
            int x = mouseLocation.x - listener.edgeStart.x;
            int y = mouseLocation.y - listener.edgeStart.y;

            int newWidth = listener.oldWidth;
            int newHeight = listener.oldHeight;
            int newLocX = listener.oldLocX;
            int newLocY = listener.oldLocY;

            switch (cursor.getType()) {
                case Cursor.S_RESIZE_CURSOR:
                    newWidth = listener.oldWidth;
                    newHeight = listener.oldHeight + y;
                    newLocX = listener.oldLocX;
                    newLocY = listener.oldLocY;
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    newWidth = listener.oldWidth + x;
                    newHeight = listener.oldHeight;
                    newLocX = listener.oldLocX;
                    newLocY = listener.oldLocY;
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    newWidth = listener.oldWidth + x;
                    newHeight = listener.oldHeight + y;
                    newLocX = listener.oldLocX;
                    newLocY = listener.oldLocY;
                    break;
                case Cursor.W_RESIZE_CURSOR:
                    newWidth = listener.oldWidth - x;
                    newHeight = listener.oldHeight;
                    newLocX = listener.oldLocX + x;
                    newLocY = listener.oldLocY;
                    break;
                case Cursor.N_RESIZE_CURSOR:
                    newWidth = listener.oldWidth;
                    newHeight = listener.oldHeight - y;
                    newLocX = listener.oldLocX;
                    newLocY = listener.oldLocY + y;
                    break;
                case Cursor.SW_RESIZE_CURSOR:
                    newWidth = listener.oldWidth - x;
                    newHeight = listener.oldHeight + y;
                    newLocX = listener.oldLocX + x;
                    newLocY = listener.oldLocY;
                    break;
                case Cursor.NW_RESIZE_CURSOR:
                    newWidth = listener.oldWidth - x;
                    newHeight = listener.oldHeight - y;
                    newLocX = listener.oldLocX + x;
                    newLocY = listener.oldLocY + y;
                    break;
                case Cursor.NE_RESIZE_CURSOR:
                    newWidth = listener.oldWidth + x;
                    newHeight = listener.oldHeight - y;
                    newLocX = listener.oldLocX;
                    newLocY = listener.oldLocY + y;
                    break;
                default:
                    break;
            }
            window.setSize(newWidth, newHeight);
            window.setLocation(newLocX, newLocY);

            window.width = newWidth;
            window.height = newHeight;

            listener.updateComponentLocationAndSize();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateHoveredComponents(e);
    }

    private void updateHoveredComponents(MouseEvent e) {
        Set<UIComponent> oldHoveredComponents = new HashSet<>(hoveredComponents);
        Set<UIComponent> newHoveredComponents = new HashSet<>();

        for (UIComponent component : mainUI.getComponentList()) {
            if (component.isInRegion(e)) {
                newHoveredComponents.add(component);
                component.setHovered(true);
            } else {
                component.setHovered(false);
            }
        }

        if (!newHoveredComponents.equals(oldHoveredComponents)) {
            hoveredComponents.clear();
            hoveredComponents.addAll(newHoveredComponents);
            mainUI.update();
            printHoveredComponents();
            listener.setCursor();
        }
    }

    private void printHoveredComponents() {
        System.out.println("Hovered component(s):");
        for (UIComponent component : hoveredComponents) {
            System.out.println("\t" + component.toString());
        }
    }
}