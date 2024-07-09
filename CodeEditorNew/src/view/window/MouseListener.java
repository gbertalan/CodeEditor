package view.window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.awt.Toolkit;

import javax.swing.JFrame;

import view.window.Window;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;

public class MouseListener extends MouseAdapter {
    private Listener listener;
    private Window window;
    private MainUI mainUI;
    private UIComponent closeButton, trayButton, maxButton, titleBar, edgeWest, edgeNorth, edgeEast, edgeSouth;
    private HashSet<UIComponent> hoveredComponents;

    public MouseListener(Listener listener) {
        this.listener = listener;
        this.window = listener.window;
        this.mainUI = listener.mainUI;
        this.hoveredComponents = listener.hoveredComponents;
        this.closeButton = listener.closeButton;
        this.trayButton = listener.trayButton;
        this.maxButton = listener.maxButton;
        this.titleBar = listener.titleBar;
        this.edgeWest = listener.edgeWest;
        this.edgeNorth = listener.edgeNorth;
        this.edgeEast = listener.edgeEast;
        this.edgeSouth = listener.edgeSouth;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        listener.unhoverAllComponents();
        mainUI.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (hoveredComponents.contains(closeButton)) {
            WindowEvent windowClosing = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosing);
        } else if (hoveredComponents.contains(trayButton)) {
            window.setExtendedState(JFrame.ICONIFIED);
        } else if (hoveredComponents.contains(maxButton)
                || (hoveredComponents.contains(titleBar) && e.getClickCount() == 2)) {
            if (!window.isMaximized()) {
                window.setMaximized(true);
                listener.oldWidth = window.width;
                listener.oldHeight = window.height;
                window.width = window.getWidth();
                window.height = window.getHeight();
            } else {
                window.setMaximized(false);
                window.width = listener.oldWidth;
                window.height = listener.oldHeight;
            }
            listener.updateComponentLocationAndSize();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (hoveredComponents.contains(titleBar) && !hoveredComponents.contains(trayButton)
                && !hoveredComponents.contains(maxButton) && !hoveredComponents.contains(closeButton)
                && !hoveredComponents.contains(edgeWest) && !hoveredComponents.contains(edgeNorth)
                && !hoveredComponents.contains(edgeEast) && !hoveredComponents.contains(edgeSouth)) {
            listener.draggingByTitleBar = true;
            listener.initialPressOnTitleBar = e.getPoint();
        }

        if (!window.isMaximized()) {
            if (hoveredComponents.contains(edgeWest) || hoveredComponents.contains(edgeNorth)
                    || hoveredComponents.contains(edgeEast) || hoveredComponents.contains(edgeSouth)) {
                listener.draggingByEdge = true;
                listener.edgeStart = e.getLocationOnScreen();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (listener.draggingByTitleBar)
            listener.draggingByTitleBar = false;

        if (listener.draggingByEdge) {
            window.width = window.getWidth();
            window.height = window.getHeight();
            window.locX = window.getLocation().x;
            window.locY = window.getLocation().y;
            listener.oldWidth = window.getWidth();
            listener.oldHeight = window.getHeight();
            listener.oldLocX = window.getLocation().x;
            listener.oldLocY = window.getLocation().y;
            listener.draggingByEdge = false;
        }
    }
}
