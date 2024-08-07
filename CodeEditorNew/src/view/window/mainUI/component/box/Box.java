package view.window.mainUI.component.box;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import control.BoxController;
import utils.Theme;
import view.window.MouseWheelListener;
import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class Box extends UIComponent {

	private static final AtomicInteger idGenerator = new AtomicInteger(0);
	private final int id;

	public static int boxCounter;

	private static final int WIDTH = 530;
	private static final int HEIGHT = 664;

	protected double doubleLocX, doubleLocY, doubleWidth, doubleHeight;

	private int mouseOffsetX;
	private int mouseOffsetY;

	private BoxHeader boxHeader;
	private BoxContent boxContent;

	private int closeButtonSize;
	private int closeButtonX;
	private int closeButtonY;
	private boolean closeButtonHovered;

	private BoxController boxController;
	Graphics2D g;
	private int scrollHorizontal;

	public Box(Window window, int drawPriority, int locX, int locY, BoxController boxController) {
		super(window, drawPriority, locX, locY, WIDTH, HEIGHT);
		this.id = idGenerator.incrementAndGet() - 1;
		this.doubleLocX = locX;
		this.doubleLocY = locY;
		this.doubleWidth = width;
		this.doubleHeight = height;
		this.boxController = boxController;
		System.out.println("New Box created with ID: " + id);
	}

	public int getId() {
		return id;
	}

	public void createHeader(String headerText) {
		boxHeader = new BoxHeader(this, headerText);
	}

	/**
	 * Creates a new BoxContent instance.
	 * 
	 * @param contentLineList
	 * @param startLineIndex
	 * @param noOfDisplayedLines
	 * @param noOfAllLines
	 */
	public void createContent(ArrayList<String> contentLineList, int startLineIndex, int noOfDisplayedLines,
			int noOfAllLines) {
		boxContent = new BoxContent(this, contentLineList, startLineIndex, noOfDisplayedLines, noOfAllLines);
		boxContent.setScrollHorizontal(scrollHorizontal);
	}

	public void updateContent(int startLineIndex, ArrayList<String> lineList) {
		scrollHorizontal = boxContent.getScrollHorizontal(); // Preserve scrollHorizontal before update
		boxContent.updateBoxContent(startLineIndex, lineList);
		boxContent.setScrollHorizontal(scrollHorizontal); // Restore scrollHorizontal after update
	}

	public void setScrollHorizontal(int value) {
		this.scrollHorizontal = value;
		if (boxContent != null) {
			boxContent.setScrollHorizontal(value);
		}
	}

	@Override
	public void draw(Graphics2D g2d) {

		this.g = g2d;

		g2d.setColor(Theme.getBoxBackgroundColor());
		g2d.fillRect(locX, locY, width, height);

		boxHeader.draw(g2d);
		boxContent.draw(g2d);

		// close button:
		closeButtonSize = (height / 16) - 2;
		closeButtonX = locX + width - closeButtonSize - 1;
		closeButtonY = locY + 1;
		int closeButtonPadding = (int) (closeButtonSize / 2.6);

		if (closeButtonHovered)
			g2d.setColor(Theme.getBoxCloseButtonColor());
		else
			g2d.setColor(Theme.getBoxBackgroundColor());
		g2d.fillRect(closeButtonX, closeButtonY, closeButtonSize, closeButtonSize);

		if (closeButtonHovered)
			g2d.setColor(Theme.getCloseButtonSymbolColorUnhovered());
		else
			g2d.setColor(Theme.getCloseButtonSymbolColor());
		g2d.drawLine(closeButtonX + closeButtonPadding, closeButtonY + closeButtonPadding,
				closeButtonX + closeButtonSize - closeButtonPadding,
				closeButtonY + closeButtonSize - closeButtonPadding);
		g2d.drawLine(closeButtonX + closeButtonSize - closeButtonPadding, closeButtonY + closeButtonPadding,
				closeButtonX + closeButtonPadding, closeButtonY + closeButtonSize - closeButtonPadding);

		// border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(locX, locY, width, height);
	}

	@Override
	public void update() {

	}

	public void updateLocation(int x, int y) { // when grabbed by the header
		repaint();
		int newX = x - mouseOffsetX;
		int newY = y - mouseOffsetY;
		setLocation(newX, newY);
		setDoubleLocation(newX, newY);
		repaint();
	}

	@Override
	public Cursor getCursor(int secondaryCursor) {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}

	public void setMouseOffset(int x, int y) {
		this.mouseOffsetX = x;
		this.mouseOffsetY = y;
	}

	public void zoom(Point mouseLocation) {
		repaint();
		adjustComponent(mouseLocation);
		repaint();
	}

	private void adjustComponent(Point mouseLocation) {
		setLocation((int) Math.round(calcX(mouseLocation.x)), (int) Math.round(calcY(mouseLocation.y)));
		setDoubleLocation(calcX(mouseLocation.x), calcY(mouseLocation.y));
		setSize((int) Math.round(calcWidth()), (int) Math.round(calcHeight()));
		setDoubleSize(calcWidth(), calcHeight());
	}

	private double calcX(int mouseX) {
		double zoom = MouseWheelListener.zoomValue;
		double componentX = getDoubleLocX();
		return mouseX + ((componentX - mouseX) * zoom);
	}

	private double calcY(int mouseY) {
		double zoom = MouseWheelListener.zoomValue;
		double componentY = getDoubleLocY();
		return mouseY + ((componentY - mouseY) * zoom);
	}

	private double calcWidth() {
		return getDoubleWidth() * MouseWheelListener.zoomValue;
	}

	private double calcHeight() {
		return getDoubleHeight() * MouseWheelListener.zoomValue;
	}

	public void setDoubleLocation(double doubleLocX, double doubleLocY) {
		this.doubleLocX = doubleLocX;
		this.doubleLocY = doubleLocY;
	}

	public void setDoubleSize(double width, double height) {
		this.doubleWidth = width;
		this.doubleHeight = height;
	}

	public double getDoubleLocX() {
		return doubleLocX;
	}

	public double getDoubleLocY() {
		return doubleLocY;
	}

	public double getDoubleWidth() {
		return doubleWidth;
	}

	public double getDoubleHeight() {
		return doubleHeight;
	}

	public void mouseMoved(MouseEvent e) {
		boolean oldCloseButtonHovered = closeButtonHovered;
		if (e.getX() > closeButtonX && e.getX() < closeButtonX + closeButtonSize && e.getY() > closeButtonY
				&& e.getY() < closeButtonY + closeButtonSize)
			closeButtonHovered = true;
		else
			closeButtonHovered = false;

		if (oldCloseButtonHovered != closeButtonHovered)
			repaint();
		// the closeButton-t can be placed in a separate class later, and repaint can be
		// called on it.

		Rectangle rect = new Rectangle(boxContent.getLocX(), boxContent.getLocY(), boxContent.getWidth(),
				boxContent.getHeight());
		if (rect.contains(e.getPoint()))
			boxContent.mouseMoved(e);
	}

	public BoxHeader getBoxHeader() {
		return this.boxHeader;
	}

	public BoxContent getBoxContent() {
		return this.boxContent;
	}

	public void unhoverCloseButton() {
		System.out.println("Box.unhoverCloseButton is called");
		if (closeButtonHovered) {
			closeButtonHovered = false;
			repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (closeButtonHovered) {
			boxController.closeBox(this);
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {

		Rectangle rect = new Rectangle(boxContent.getLocX(), boxContent.getLocY(), boxContent.getWidth(),
				boxContent.getHeight());
		if (rect.contains(e.getPoint()))
			boxContent.mouseWheelMoved(e);

	}

	public Graphics2D getGraphics() {
		return (Graphics2D) window.getMainUI().getGraphics();
	}

	public BoxController getBoxController() {
		return boxController;
	}

	public double getScrollHorizontal() {
		return scrollHorizontal;
	}

}
