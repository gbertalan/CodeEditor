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
	private int height = 29;
	private JPanel parent;
	
	public FButton(String filename, int yPosition, JPanel parent) {
		this.filename = filename;
		this.y = yPosition;
		this.parent = parent;
		width = parent.getWidth();
	}
	
	public void draw(Object hoveredComponent, Graphics2D g2d) {
		g2d.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		g2d.setColor(Theme.getPanelButtonColor());
		g2d.setColor(Color.BLUE);
		
		if (hoveredComponent!=null && hoveredComponent.equals(this)) {
			g2d.setColor(Theme.getPanelButtonHoverColor());
		}
		else
			g2d.setColor(Theme.getPanelButtonColor());
		g2d.fillRect(0, y, width, height);
		g2d.setColor(Theme.getPanelTextColor());
		g2d.drawString(filename, x, y + 20);
	}
	
	public boolean containsPoint(Point point) {
		if (point.x >= x && point.y >= y && point.x <= x + width && point.y <= y + height)
			return true;
		else
			return false;
	}
}
