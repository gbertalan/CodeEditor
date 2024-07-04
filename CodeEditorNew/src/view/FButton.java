package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import utils.Theme;

public class FButton {

	private String filename;
	private int x = 30;
	private int y;
	private int width;
	public static int HEIGHT = 30;
	private JPanel parent;
	private static int wheelRotationAmount;

	public FButton(String filename, int yPosition, JPanel parent) {
		this.filename = filename;
		this.y = yPosition;
		this.parent = parent;
		width = parent.getWidth();
	}

	public void draw(Object hoveredComponent, Graphics2D g2d) {
		g2d.setFont(new Font("Consolas", Font.BOLD, 15));

		g2d.setColor(Theme.getPanelButtonColor());
		g2d.setColor(Color.BLUE);

		if (hoveredComponent != null && hoveredComponent.equals(this)) {
			g2d.setColor(Theme.getPanelButtonHoverColor());
		} else
			g2d.setColor(Theme.getPanelButtonColor());
		g2d.fillRect(0, y + wheelRotationAmount, width, HEIGHT);
		g2d.setColor(Theme.getPanelTextColor());
		g2d.drawString(filename, x, y + wheelRotationAmount + 20);
	}

	public boolean containsPoint(Point point) {
		if (point.x >= x && point.y >= y + wheelRotationAmount && point.x <= x + width
				&& point.y <= y + wheelRotationAmount + HEIGHT)
			return true;
		else
			return false;
	}

	public String getFilename() {
		return filename;
	}

	public static void setWheelRotationAmount(int amount) {
		wheelRotationAmount += amount;
	}

	public static int getWheelRotationAmount() {
		return wheelRotationAmount;
	}
	
	public int getExactYLoc() {
		return y + wheelRotationAmount;
	}

}
