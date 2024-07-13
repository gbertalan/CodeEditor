package view.window.mainUI.component.box;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class Box extends UIComponent {

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
	private ArrayList<UIComponent> outputs = new ArrayList<>();

	private int noOfInputs; // to handle where to draw the boxInputs

	private ArrayList<int[]> initialInputPositions = new ArrayList<>();
	private int[] initialHeaderPosition;
	private int[] initialContentPosition;
	private int[] initialConsolePosition;
	private ArrayList<int[]> initialOutputPositions = new ArrayList<>();

	private int mouseOffsetX;
	private int mouseOffsetY;

	public Box(Window window, int drawPriority, int locX, int locY, String filename) {
		super(window, drawPriority, locX, locY, WIDTH, HEIGHT);

		UIComponent boxInput0 = new BoxInput(window, drawPriority + 1, locX, locY - INPUT_HEIGHT, INPUT_WIDTH,
				INPUT_HEIGHT);
		inputs.add(boxInput0);
		initialInputPositions.add(new int[] { boxInput0.getLocX() - locX, boxInput0.getLocY() - locY });
		window.getMainUI().addComponent(boxInput0);

		boxHeader = new BoxHeader(window, drawPriority + 1, locX, locY, HEADER_WIDTH, HEADER_HEIGHT, filename);
		initialHeaderPosition = new int[] { boxHeader.getLocX() - locX, boxHeader.getLocY() - locY };
		window.getMainUI().addComponent(boxHeader);

		boxContent = new BoxContent(window, drawPriority + 1, locX, locY + HEADER_HEIGHT, CONTENT_WIDTH,
				CONTENT_HEIGHT);
		initialContentPosition = new int[] { boxContent.getLocX() - locX, boxContent.getLocY() - locY };
		window.getMainUI().addComponent(boxContent);

		boxConsole = new BoxConsole(window, drawPriority + 1, locX, locY + HEADER_HEIGHT + CONTENT_HEIGHT,
				CONSOLE_WIDTH, CONSOLE_HEIGHT);
		initialConsolePosition = new int[] { boxConsole.getLocX() - locX, boxConsole.getLocY() - locY };
		window.getMainUI().addComponent(boxConsole);

		UIComponent boxOutput0 = new BoxOutput(window, drawPriority + 1, locX + WIDTH,
				boxHeader.getLocY() + (boxHeader.getHeight() / 2) - (OUTPUT_HEIGHT / 2), OUTPUT_WIDTH, OUTPUT_HEIGHT);
		outputs.add(boxOutput0);
		initialOutputPositions.add(new int[] { boxOutput0.getLocX() - locX, boxOutput0.getLocY() - locY });
		window.getMainUI().addComponent(boxOutput0);

		UIComponent boxOutput1 = new BoxOutput(window, drawPriority + 1, locX + WIDTH,
				boxContent.getLocY() + (boxContent.getHeight() / 2) - (OUTPUT_HEIGHT / 2), OUTPUT_WIDTH, OUTPUT_HEIGHT);
		outputs.add(boxOutput1);
		initialOutputPositions.add(new int[] { boxOutput1.getLocX() - locX, boxOutput1.getLocY() - locY });
		window.getMainUI().addComponent(boxOutput1);

		UIComponent boxOutput2 = new BoxOutput(window, drawPriority + 1, locX + WIDTH,
				boxConsole.getLocY() + (boxConsole.getHeight() / 2) - (OUTPUT_HEIGHT / 2), OUTPUT_WIDTH, OUTPUT_HEIGHT);
		outputs.add(boxOutput2);
		initialOutputPositions.add(new int[] { boxOutput2.getLocX() - locX, boxOutput2.getLocY() - locY });
		window.getMainUI().addComponent(boxOutput2);

		window.getMainUI().update();
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

	@Override
	public void updateLocation(int x, int y) {
		int newX = x - mouseOffsetX;
		int newY = y - mouseOffsetY;

		for (int i = 0; i < inputs.size(); i++) {
			BoxInput input = (BoxInput) inputs.get(i);
			int[] initialPosition = initialInputPositions.get(i);
			input.updateLocation(newX + initialPosition[0], newY + initialPosition[1]);
			input.updateArrowLocation(newX + initialPosition[0], newY + initialPosition[1]);
			input.repaint();
		}

		boxHeader.updateLocation(newX + initialHeaderPosition[0], newY + initialHeaderPosition[1]);
		boxContent.updateLocation(newX + initialContentPosition[0], newY + initialContentPosition[1]);
		boxConsole.updateLocation(newX + initialConsolePosition[0], newY + initialConsolePosition[1]);

		for (int i = 0; i < outputs.size(); i++) {
			BoxOutput output = (BoxOutput) outputs.get(i);
			int[] initialPosition = initialOutputPositions.get(i);
			output.updateLocation(newX + initialPosition[0], newY + initialPosition[1]);
			output.updateArrowLocation(newX + initialPosition[0], newY + initialPosition[1]);
		}

		repaint();
		window.getMainUI().update();
		System.out.println("Box: " + newX);
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
}
