package view.window.mainUI.component.box;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import utils.ANSIText;
import view.window.MouseWheelListener;
import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class Box extends UIComponent {

	public static int boxCounter;

	public static float LINE_THICK = 8.0f;
	public static float LINE_NORMAL = 1.0f;

	private static int WIDTH = 280;

	private static int INPUT_WIDTH = WIDTH;
	private static int INPUT_HEIGHT = 30;
	private static int HEADER_WIDTH = WIDTH;
	private static int HEADER_HEIGHT = 40;
	private static int CONTENT_WIDTH = WIDTH;
	private static int CONTENT_HEIGHT = 300;
	private static int CONSOLE_WIDTH = WIDTH;
	private static int CONSOLE_HEIGHT = 300;
	private static int OUTPUT_WIDTH = 50;
	private static int OUTPUT_HEIGHT = INPUT_HEIGHT;
	private static int HEIGHT = HEADER_HEIGHT + CONTENT_HEIGHT + CONSOLE_HEIGHT;

	private ArrayList<UIComponent> inputs = new ArrayList<>();
	private UIComponent boxHeader;
	private UIComponent boxContent;
	private UIComponent boxConsole;

	private int noOfInputs; // to handle where to draw the boxInputs

	private ArrayList<int[]> initialInputPositions = new ArrayList<>();
	private int[] initialHeaderPosition;
	private int[] initialContentPosition;
	private int[] initialConsolePosition;

	private int mouseOffsetX;
	private int mouseOffsetY;

	private CopyOnWriteArrayList<UIComponent> boxSubComponentList = new CopyOnWriteArrayList<>();

	public Box(Window window, int drawPriority, int locX, int locY, String filename) {
		super(window, drawPriority, locX, locY, WIDTH, HEIGHT);

		setDoubleLocation(locX, locY);
		setDoubleSize(WIDTH, HEIGHT);

		UIComponent boxInput0 = new BoxInput(window, drawPriority, locX, locY - INPUT_HEIGHT, INPUT_WIDTH,
				INPUT_HEIGHT);
		inputs.add(boxInput0);
		initialInputPositions.add(new int[] { boxInput0.getLocX() - locX, boxInput0.getLocY() - locY });
		window.getMainUI().addComponent(boxInput0);

		boxHeader = new BoxHeader(window, drawPriority, locX, locY, HEADER_WIDTH, HEADER_HEIGHT, filename);
		initialHeaderPosition = new int[] { boxHeader.getLocX() - locX, boxHeader.getLocY() - locY };
		window.getMainUI().addComponent(boxHeader);

		boxContent = new BoxContent(window, drawPriority, locX, locY + HEADER_HEIGHT, CONTENT_WIDTH, CONTENT_HEIGHT);
		initialContentPosition = new int[] { boxContent.getLocX() - locX, boxContent.getLocY() - locY };
		window.getMainUI().addComponent(boxContent);

		boxConsole = new BoxConsole(window, drawPriority, locX, locY + HEADER_HEIGHT + CONTENT_HEIGHT, CONSOLE_WIDTH,
				CONSOLE_HEIGHT);
		initialConsolePosition = new int[] { boxConsole.getLocX() - locX, boxConsole.getLocY() - locY };
		window.getMainUI().addComponent(boxConsole);

		// Add all components to the boxSubComponentList
		boxSubComponentList.add(boxInput0);
		boxSubComponentList.add(boxHeader);
		boxSubComponentList.add(boxContent);
		boxSubComponentList.add(boxConsole);

		repaintBoxComponents();
	}

	@Override
	public void draw(Graphics2D g2d) {
//        g2d.setColor(Color.WHITE);
//        g2d.drawRect(locX, locY, width, height);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	public CopyOnWriteArrayList<UIComponent> getBoxSubComponentList() {
		return boxSubComponentList;
	}

	public void repaintBoxComponents() {

//		for (UIComponent uiComponent : boxSubComponentList) {
//			uiComponent.repaint();
//		}

		for (int i = 0; i < inputs.size(); i++) {
			BoxInput input = (BoxInput) inputs.get(i);
			input.repaint();
		}
		boxHeader.repaint();
		boxContent.repaint();
		boxConsole.repaint();
	}

	@Override
	public void updateLocation(int x, int y) { // when e.g. dragging

		repaintBoxComponents();

		int newX = x - mouseOffsetX;
		int newY = y - mouseOffsetY;

		for (int i = 0; i < inputs.size(); i++) {
			BoxInput input = (BoxInput) inputs.get(i);
			int[] initialPosition = initialInputPositions.get(i);
			input.updateLocation(newX + initialPosition[0], newY + initialPosition[1]);
		}

		boxHeader.updateLocation(newX + initialHeaderPosition[0], newY + initialHeaderPosition[1]);
		boxContent.updateLocation(newX + initialContentPosition[0], newY + initialContentPosition[1]);
		boxConsole.updateLocation(newX + initialConsolePosition[0], newY + initialConsolePosition[1]);

		repaintBoxComponents();
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		// TODO Auto-generated method stub
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}

	public void setMouseOffset(int x, int y) {
		// TODO Auto-generated method stub
		this.mouseOffsetX = x;
		this.mouseOffsetY = y;
	}

	public void zoom(Point mouseLocation) {
		repaintBoxComponents();

//		if (!mouseLocation.equals(oldMouseLocation)) {
//			setInitialLocation(locX-(mouseLocation.x- locX), locY-(mouseLocation.y- locY));
//			oldMouseLocation = mouseLocation;
//		}

		// Update location and size for each component
		adjustComponent(boxHeader, mouseLocation);
		adjustComponent(boxContent, mouseLocation);
		adjustComponent(boxConsole, mouseLocation);
		
		

		repaintBoxComponents();
		
		System.out.println(ANSIText.cyan("Box.getLocX(): "+ boxHeader.getDoubleLocX()));
		System.out.println(ANSIText.cyan("Box.getLocY(): "+ boxHeader.getDoubleLocY()));
		System.out.println(ANSIText.cyan("Box.getWidth(): "+ boxHeader.getDoubleWidth()));
		System.out.println(ANSIText.cyan("Box.getHeight(): "+ boxHeader.getDoubleHeight()));
	}

	private void adjustComponent(UIComponent component, Point mouseLocation) {
//		component.setInitialLocation(component.getLocX(), component.getLocY());
		component.setLocation((int)Math.round(calcX(component, mouseLocation.x)), (int)Math.round(calcY(component, mouseLocation.y)));
		component.setDoubleLocation(calcX(component, mouseLocation.x), calcY(component, mouseLocation.y));

//		component.setInitialSize(component.getWidth(), component.getHeight());
		component.setSize((int)Math.round(calcWidth(component)), (int)Math.round(calcHeight(component)));
		component.setDoubleSize(calcWidth(component), calcHeight(component));

		
	}

	private double calcX(UIComponent component, int mouseX) {
		double zoom = MouseWheelListener.zoomValue;
		double componentX = component.getDoubleLocX();
		return (mouseX + ((componentX - mouseX) * zoom));
	}

	private double calcY(UIComponent component, int mouseY) {
		double zoom = MouseWheelListener.zoomValue;
		double componentY = component.getDoubleLocY();
		return (mouseY + ((componentY - mouseY) * zoom));
	}

	private double calcWidth(UIComponent component) {
		double zoom = MouseWheelListener.zoomValue;
		return (component.getDoubleWidth() * zoom);
	}

	private double calcHeight(UIComponent component) {
		double zoom = MouseWheelListener.zoomValue;
		return (component.getDoubleHeight() * zoom);
	}

}
