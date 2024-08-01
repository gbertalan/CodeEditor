package view.window;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import control.BoxController;
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
	boolean draggingByTitleBar, draggingByEdge, draggingByBoxHeader, draggingByBackground;
	HashSet<UIComponent> hoveredComponents = new HashSet<>();
	public int dragginBoxID;
	public BoxController boxController;
	public UIComponent background, closeButton, edgeEast, edgeNorth, edgeSouth, edgeWest, fileButton, footer, maxButton,
			sidePanelLeft, sidePanelRight, titleBar, trayButton;

	public Listener(Window window) {
		System.out.println(ANSIText.purple("Listener constructor is called."));
		this.window = window;
		this.mainUI = window.getMainUI();

		oldWidth = window.width;
		oldHeight = window.height;
		oldLocX = window.locX;
		oldLocY = window.locY;

		initializeComponents();

	}

	void initializeComponents() {
		background = mainUI.getComponent("Background");
		closeButton = mainUI.getComponent("CloseButton");
		edgeEast = mainUI.getComponent("EdgeEast");
		edgeNorth = mainUI.getComponent("EdgeNorth");
		edgeSouth = mainUI.getComponent("EdgeSouth");
		edgeWest = mainUI.getComponent("EdgeWest");
		fileButton = mainUI.getComponent("FileButton");
		footer = mainUI.getComponent("Footer");
		maxButton = mainUI.getComponent("MaxButton");
		sidePanelLeft = mainUI.getComponent("SidePanelLeft");
		sidePanelRight = mainUI.getComponent("SidePanelRight");
		titleBar = mainUI.getComponent("TitleBar");
		trayButton = mainUI.getComponent("TrayButton");
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

	public void setBoxController(BoxController boxController) {
		this.boxController = boxController;
	}

	/**
	 * Propagates an event to the first hovered component that is a Box. The
	 * propagated method is sent in as a parameter.
	 *
	 * @param <E> the type of event to be propagated
	 * @param e the event to be propagated
	 * @param eventHandler the method we want to call. e.g. Box::mouseClicked or Box::mouseWheelMoved
	 */
	public <E> void propagateEvent(E e, BiConsumer<Box, E> eventHandler) {
	    for (UIComponent uiComponent : hoveredComponents) {
	        if (uiComponent.toString().startsWith("Box")) {
	            Box box = (Box) uiComponent;
	            eventHandler.accept(box, e);
	            break;
	        }
	    }
	}
	
	/**
	 * Returns the UIComponent with the highest priority
	 * @return the UIComponent with the highest priority, or null if no components are hovered
	 */
	public UIComponent getTopHoveredComponent() {
	    UIComponent top = null;
	    for (UIComponent uiComponent : hoveredComponents) {
	        if (top == null || uiComponent.getDrawPriority() > top.getDrawPriority()) {
	            top = uiComponent;
	        }
	    }
	    return top;
	}

}
