package view.window;

import java.awt.Cursor;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;

import utils.ANSIText;
import view.window.Window;
import view.window.mainUI.MainUI;
import view.window.mainUI.component.UIComponent;
import view.window.mainUI.component.box.Box;

public class Listener {
	Window window;
	MainUI mainUI;
	Point initialPressOnTitleBar = new Point(0, 0);
	Point edgeStart = new Point(0, 0);
	int oldWidth, oldHeight, oldLocX, oldLocY;
	boolean draggingByTitleBar, draggingByEdge, draggingByBoxHeader;
	HashSet<UIComponent> hoveredComponents = new HashSet<>();

	public Listener(Window window) {
		System.out.println(ANSIText.purple("Listener constructor is called."));
		this.window = window;
		this.mainUI = window.getMainUI();

		oldWidth = window.width;
		oldHeight = window.height;
		oldLocX = window.locX;
		oldLocY = window.locY;
	}

	void updateComponentLocationAndSize() {
		allUIComponent(UIComponent::update);
	}

	void allUIComponent(Consumer<UIComponent> action) {
		for (UIComponent component : mainUI.getComponentList()) {
			action.accept(component);
		}
	}

	void setCursor() {
		if (hoveredComponents.contains(mainUI.getComponent("EdgeSouth"))) {
			if (hoveredComponents.contains(mainUI.getComponent("EdgeWest"))) {
				window.setCursor(mainUI.getComponent("EdgeSouth").getCursor(Cursor.W_RESIZE_CURSOR));
			} else if (hoveredComponents.contains(mainUI.getComponent("EdgeEast"))) {
				window.setCursor(mainUI.getComponent("EdgeSouth").getCursor(Cursor.E_RESIZE_CURSOR));
			} else {
				window.setCursor(mainUI.getComponent("EdgeSouth").getCursor());
			}
		} else if (hoveredComponents.contains(mainUI.getComponent("EdgeNorth"))) {
			if (hoveredComponents.contains(mainUI.getComponent("EdgeWest"))) {
				window.setCursor(mainUI.getComponent("EdgeNorth").getCursor(Cursor.W_RESIZE_CURSOR));
			} else if (hoveredComponents.contains(mainUI.getComponent("EdgeEast"))) {
				window.setCursor(mainUI.getComponent("EdgeNorth").getCursor(Cursor.E_RESIZE_CURSOR));
			} else {
				window.setCursor(mainUI.getComponent("EdgeNorth").getCursor());
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
		return getCursorPriority(cursor1) < getCursorPriority(cursor2);
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
