package view.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import utils.Theme;

public class PanelButton {

	private int x;
	private int y;
	private int width;
	private int height;
	private String text;
	private JPanel parent;

	private static int HEIGHT = 30;

	public PanelButton(String text, int yPosition, JPanel parent) {
		this.text = text;
		this.y = yPosition;
		this.parent = parent;
		width = parent.getWidth() - 30 + 2;
		height = HEIGHT;
	}

	public void draw(Object hoveredComponent, Graphics2D g2d) {
		int arc = 4;

		int x = (parent.getWidth() - width) / 2;

		if (hoveredComponent!=null && hoveredComponent.equals(this)) {
			g2d.setColor(Theme.getPanelButtonHoverColor());
		}
		else
			g2d.setColor(Theme.getPanelButtonColor());
		g2d.fillRoundRect(x, y, width, height, arc, arc);

		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRoundRect(x, y, width, height, arc, arc);

		g2d.setColor(Theme.getPanelTextColor());
		g2d.setFont(new Font("Consolas", Font.BOLD, 14));
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		int textHeight = fm.getAscent();
		g2d.drawString(text, x + (width / 2) - (textWidth / 2), y + (height / 2) + (textHeight / 2));
	}

	public boolean containsPoint(Point point) {
		if (point.x >= x && point.y >= y && point.x <= x + width && point.y <= y + height)
			return true;
		else
			return false;
	}
	
	public void click() {
		System.out.println("clicked: "+ this.getClass());
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public String getText() {
		return text;
	}
}
