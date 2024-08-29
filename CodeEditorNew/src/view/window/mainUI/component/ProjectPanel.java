package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.util.ArrayList;

import utils.Theme;
import view.window.Window;
import view.window.mainUI.component.box.Box;
import view.window.mainUI.component.box.BoxContent;

public class ProjectPanel extends UIComponent {

	private static int TOP_MARGIN = 42;
	private static int LEFT_MARGIN = 8;
	private static int WIDTH = 220;
	private static int BOTTOM_MARGIN = 220+9;

	private PanelButton projectButton;
	BoxContent boxContent;

	public ProjectPanel(Window window, int drawPriority) {
		super(window, drawPriority, LEFT_MARGIN, TOP_MARGIN, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN);

		projectButton = new PanelButton(window, drawPriority+1, locX+20, locY+20, width-40, 30, "OPEN PROJECT");
		
		ArrayList<String> contentLineList = new ArrayList<>();
		contentLineList.add("one");
		contentLineList.add("two");
		contentLineList.add("three");
		createContent(contentLineList, 1, 5, 5);

	}

	public void createContent(ArrayList<String> contentLineList, int startLineIndex, int noOfDisplayedLines,
			int noOfAllLines) {
		Box box = new Box(window, drawPriority, locX, locY, width, height, null);
		box.createHeader("Project");
		boxContent = new BoxContent(box, contentLineList, startLineIndex,
				noOfDisplayedLines, noOfAllLines);
	}

	@Override
	public void draw(Graphics2D g2d) {

		// background:
		g2d.setColor(Theme.getSidePanelColor());
		g2d.fillRect(locX, locY, width, height);
		
		// button:
		projectButton.draw(g2d);

		// separator:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(locX, locY + (height / 2), locX + width, locY + (height / 2));

		// border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(locX, locY, width, height);
		
//		boxContent.draw(g2d);
	}

	@Override
	public void update() {
		height = window.height - TOP_MARGIN - BOTTOM_MARGIN;
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	}

}
