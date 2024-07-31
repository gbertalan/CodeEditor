package view.window.mainUI.component.box;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import utils.Globals;
import utils.Theme;
import view.window.MouseWheelListener;

public class BoxContent implements BoxComponent {

	private Box box;

	private int locX;
	private int locY;
	private int width;
	private int height;

	private ArrayList<DisplayedLine> displayedLines;
	private BufferedImage contentImage;

	private ScrollerVertical scrollerVertical;

	private int noOfAllLines;

	private int noOfDisplayedLines;

	private int noOfDisplayedColumns = 50;

	private int noOfAllColumns = 100;

	private ScrollerHorizontal scrollerHorizontal;

	private ArrayList<String> lineList;

	private int startLineIndex;

	private int scrollHorizontal;

	public BoxContent(Box box, ArrayList<String> lineList, int startLineIndex, int noOfDisplayedLines,
			int noOfAllLines) {
		this.box = box;
		this.lineList = lineList;
		this.startLineIndex = startLineIndex;

		this.noOfDisplayedLines = noOfDisplayedLines;
		this.noOfAllLines = noOfAllLines;

		this.displayedLines = new ArrayList<>();

		// Initialize location and size
		updateLocationAndSize();

		this.scrollHorizontal = 0;

		// Create displayed lines, unbroken:
		int lineIndex = 0;
		for (String line : lineList) {
			DisplayedLine displayedLine = new DisplayedLine(box, startLineIndex + lineIndex + 1, line, lineIndex);
			displayedLine.getLineTextContainer().setScrollHorizontal(scrollHorizontal);
			displayedLines.add(displayedLine);
			++lineIndex;
		}

		// Create image
		createImage();

		scrollerVertical = new ScrollerVertical(this);
		scrollerHorizontal = new ScrollerHorizontal(this);
	}

	public void updateBoxContent(int startLineIndex, ArrayList<String> lineList) {
		this.startLineIndex = startLineIndex;
		createDisplayedLines(lineList, startLineIndex);
		createImage();
	}

	private void createDisplayedLines(ArrayList<String> lineList, int startLineIndex) {
		displayedLines.clear();
		int lineIndex = 0;
		for (String line : lineList) {
			DisplayedLine displayedLine = new DisplayedLine(box, startLineIndex + lineIndex + 1, line, lineIndex);
			displayedLine.getLineTextContainer().setScrollHorizontal(scrollHorizontal); // Set scrollHorizontal
			displayedLines.add(displayedLine);
			++lineIndex;
		}
	}

	public void setScrollHorizontal(int value) {
		this.scrollHorizontal = value;
		for (DisplayedLine displayedLine : displayedLines) {
			displayedLine.getLineTextContainer().setScrollHorizontal(scrollHorizontal); // Update all displayed lines
		}
	}

	public int getScrollHorizontal() {
		return scrollHorizontal;
	}

	private void updateLocationAndSize() {
		locX = box.getLocX();
		locY = box.getLocY() + box.getBoxHeader().getHeight();
		width = box.getWidth();
		height = box.getHeight() - box.getBoxHeader().getHeight();
	}

	public void createImage() {
		updateLocationAndSize();

		contentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = contentImage.createGraphics();

		Globals.setRenderingHints(g2d);

		// Draw displayed lines
		for (DisplayedLine displayedLine : displayedLines) {
			displayedLine.draw(g2d);
		}

		g2d.dispose();
	}
	
	public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage copy = new BufferedImage(
            source.getWidth(), 
            source.getHeight(), 
            source.getType()
        );
        Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        return copy;
    }

	@Override
	public void draw(Graphics2D g2d) {

		updateLocationAndSize();

		// Draw background
		g2d.setColor(Theme.getBoxBackgroundColor());
		g2d.fillRect(locX, locY, width, height);

		BufferedImage imageToDraw = contentImage;
		if (contentImage.getWidth() != width || contentImage.getHeight() != height) {
			imageToDraw = Globals.resize(contentImage, width, height);
		}

		g2d.drawImage(imageToDraw, locX, locY, null);

		scrollerVertical.draw(g2d);
		scrollerHorizontal.draw(g2d);
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNoOfDisplayedLines() {
		return noOfDisplayedLines;
	}

	public int getNoOfAllLines() {
		return noOfAllLines;
	}

	public int getNoOfDisplayedColumns() {
		return noOfDisplayedColumns;
	}

	public int getNoOfAllColumns() {
		return noOfAllColumns;
	}

	public int getStartLine() {
		return startLineIndex;
	}

	public Box getBox() {
		return box;
	}

	public DisplayedLine getDisplayedLine(int i) {
		return displayedLines.get(i);
	}

	public int getLineNumberContainerWidth() {
		return box.getWidth() / 8;
	}

//	public void setScrollHorizontal(int value) {
//		this.scrollHorizontal = value;
//	}

	public void mouseMoved(MouseEvent e) {
		System.out.println("Mouse in boxContent moved.");

//		if (e.getX() > scrollerVertical.getLocX())
//			scrollerVertical.mouseMoved(e);
//		else if (e.getY() > scrollerHorizontal.getLocY())
//			scrollerHorizontal.mouseMoved(e);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("MouseWheel in boxContent moved.");
		if (!e.isControlDown()) {
			if (!e.isShiftDown()) {
//				scrollerVertical.scroll(e.getUnitsToScroll());
//				scrollerVertical.setYShift(50);
				scrollerVertical.scroll(e.getUnitsToScroll());
				box.repaint();
			} else {
				scrollerHorizontal.scroll(e.getUnitsToScroll());
				box.repaint();
			}
		}
	}

	public ScrollerVertical getScrollerVertical() {
		return scrollerVertical;
	}

	public ScrollerHorizontal getScrollerHorizontal() {
		return scrollerHorizontal;
	}

}
