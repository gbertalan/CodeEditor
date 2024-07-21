package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import utils.Theme;
import view.window.MouseWheelListener;
import view.window.Window;
import view.window.mainUI.component.UIComponent;

public class Box extends UIComponent {

	public static int boxCounter;

	private static final int WIDTH = 530;
	private static final int HEIGHT = 640;

	protected double doubleLocX, doubleLocY, doubleWidth, doubleHeight;

	private int mouseOffsetX;
	private int mouseOffsetY;

	private BoxHeader boxHeader;
	private BoxContent boxContent;

	private int closeButtonSize;
	private int closeButtonX;
	private int closeButtonY;
	private boolean closeButtonHovered;

	public Box(Window window, int drawPriority, int locX, int locY) {
		super(window, drawPriority, locX, locY, WIDTH, HEIGHT);
		this.doubleLocX = locX;
		this.doubleLocY = locY;
		this.doubleWidth = width;
		this.doubleHeight = height;
	}

	public void createHeader(String headerText) {
		boxHeader = new BoxHeader(this, headerText);
	}

	public void createContent(ArrayList<String> contentLineList) {
		boxContent = new BoxContent(this, contentLineList);
	}

	@Override
	public void draw(Graphics2D g2d) {

		g2d.setColor(Theme.getBackgroundColor());
		g2d.fillRect(locX, locY, width, height);

		boxHeader.draw(g2d);
		boxContent.draw(g2d);

		// close button:
		closeButtonSize = height / 16;
		closeButtonX = locX + width - closeButtonSize;
		closeButtonY = locY;
		int closeButtonPadding = (int) (closeButtonSize / 2.6); // Adjust closeButtonPadding to make the "X" smaller

		if (closeButtonHovered)
			g2d.setColor(Color.BLUE);
		else
			g2d.setColor(Color.YELLOW);
		g2d.fillRect(closeButtonX, closeButtonY, closeButtonSize, closeButtonSize);

		g2d.setColor(Color.BLACK);
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

	public void updateLocation(int x, int y) {
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
		if (e.getX() > closeButtonX && e.getX() < closeButtonX + closeButtonSize && e.getY() > closeButtonY
				&& e.getY() < closeButtonY + closeButtonSize)
			closeButtonHovered = true;
		else
			closeButtonHovered = false;
		
		repaint();// ne hivjuk meg folyton
	}

	public BoxHeader getBoxHeader() {
		return this.boxHeader;
	}

	public BoxContent getBoxContent() {
		return this.boxContent;
	}

	public void unhoverCloseButton() {
		System.out.println("Box.unhoverCloseButton is called");
		closeButtonHovered = false;
		repaint();
	}

}
