package view.window;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowStateListener;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.JFrame;

import utils.ANSIText;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;
import view.window.Window;

public class Listener extends MouseAdapter {
    public Window window;

    public Point initialPressOnTitleBar = new Point(0, 0);
    public static Point mouseMoved = new Point(0, 0);
    public static Point mouseClicked = new Point(0, 0);
    public static Point mouseDragged = new Point(0, 0);
    public static Point mouseReleased = new Point(0, 0);
    public static boolean mouseExited;

    public Point edgeStart = new Point(0, 0);

    public int oldWidth;
    public int oldHeight;
    public int oldLocX;
    public int oldLocY;

    public boolean draggingByTitleBar;
    public boolean draggingByEdge;

    public MainUI mainUI;
    public UIComponent titleBar, closeButton, trayButton, maxButton, sidePanelLeft, sidePanelRight, fileButton, footer,
            edgeWest, edgeNorth, edgeEast, edgeSouth;

    public HashSet<UIComponent> hoveredComponents = new HashSet<>();

    public Listener(Window window) {
        System.out.println(ANSIText.red("MainUIListener constructor is called."));

        this.window = window;
        this.mainUI = window.getMainUI();

        initializeComponents();

        oldWidth = window.width;
        oldHeight = window.height;
        oldLocX = window.locX;
        oldLocY = window.locY;
    }

    public void initializeComponents() {
        edgeWest = mainUI.getComponent("EdgeWest");
        edgeNorth = mainUI.getComponent("EdgeNorth");
        edgeEast = mainUI.getComponent("EdgeEast");
        edgeSouth = mainUI.getComponent("EdgeSouth");
        titleBar = mainUI.getComponent("TitleBar");
        closeButton = mainUI.getComponent("CloseButton");
        trayButton = mainUI.getComponent("TrayButton");
        maxButton = mainUI.getComponent("MaxButton");
        sidePanelLeft = mainUI.getComponent("SidePanelLeft");
        sidePanelRight = mainUI.getComponent("SidePanelRight");
        fileButton = mainUI.getComponent("FileButton");
        footer = mainUI.getComponent("Footer");

    }

    public void updateComponentLocationAndSize() {
        allUIComponent(UIComponent -> UIComponent.update());
    }

    private void allUIComponent(Consumer<UIComponent> action) {
        for (UIComponent component : mainUI.getComponentList()) {
            action.accept(component);
        }
    }

    public void setCursor() {
        if (hoveredComponents.contains(edgeSouth)) {
            if (hoveredComponents.contains(edgeWest)) {
                window.setCursor(edgeSouth.getCursor(Cursor.W_RESIZE_CURSOR));
            } else if (hoveredComponents.contains(edgeEast)) {
                window.setCursor(edgeSouth.getCursor(Cursor.E_RESIZE_CURSOR));
            } else {
                window.setCursor(edgeSouth.getCursor());
            }
        } else if (hoveredComponents.contains(edgeNorth)) {
            if (hoveredComponents.contains(edgeWest)) {
                window.setCursor(edgeNorth.getCursor(Cursor.W_RESIZE_CURSOR));
            } else if (hoveredComponents.contains(edgeEast)) {
                window.setCursor(edgeNorth.getCursor(Cursor.E_RESIZE_CURSOR));
            } else {
                window.setCursor(edgeNorth.getCursor());
            }
        } else {
            Cursor highestPriorityCursor = null;
            for (UIComponent component : hoveredComponents) {
                Cursor cursor = component.getCursor();
                if (highestPriorityCursor == null || isHigherPriority(cursor, highestPriorityCursor)) {
                    highestPriorityCursor = cursor;
                }
            }
            if (highestPriorityCursor != null) {
                window.setCursor(highestPriorityCursor);
            } else {
                window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    private boolean isHigherPriority(Cursor cursor1, Cursor cursor2) {
        int priority1 = getCursorPriority(cursor1);
        int priority2 = getCursorPriority(cursor2);
        return priority1 < priority2;
    }

    private int getCursorPriority(Cursor cursor) {
        switch (cursor.getType()) {
            case Cursor.N_RESIZE_CURSOR:
            case Cursor.S_RESIZE_CURSOR:
            case Cursor.W_RESIZE_CURSOR:
            case Cursor.E_RESIZE_CURSOR:
            case Cursor.NE_RESIZE_CURSOR:
            case Cursor.NW_RESIZE_CURSOR:
            case Cursor.SE_RESIZE_CURSOR:
            case Cursor.SW_RESIZE_CURSOR:
                return 1;
            case Cursor.HAND_CURSOR:
                return 2;
            case Cursor.MOVE_CURSOR:
                return 3;
            case Cursor.TEXT_CURSOR:
                return 4;
            case Cursor.WAIT_CURSOR:
                return 5;
            case Cursor.CROSSHAIR_CURSOR:
                return 6;
            case Cursor.DEFAULT_CURSOR:
                return 7;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public void unhoverAllComponents() {
        for (UIComponent component : hoveredComponents) {
            component.setHovered(false);
        }
        hoveredComponents.clear();
    }
}
