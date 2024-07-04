package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import utils.Theme;

public class ScrollableButton {

	private static final int TEXT_LEFT_MARGIN = 30;
	private static final int HEIGHT = 30;

	private int x, y, width, height;
	private String filename;

	private Graphics2D g2d;
	
	JPanel parent;

	public ScrollableButton(int id, String filename, JPanel parent) {
		x = 0;
		y = id * HEIGHT;
		width = parent.getWidth();
		height = HEIGHT;
		this.filename = filename;
		this.parent = parent;
	}

	public void draw(Object hoveredComponent, Graphics2D g2d) {

		g2d.setFont(new Font("Consolas", Font.BOLD, 15));

		g2d.setColor(Theme.getPanelButtonColor());
//		g2d.setColor(Color.BLUE);

		if (hoveredComponent != null && hoveredComponent.equals(this)) {
			g2d.setColor(Theme.getPanelButtonHoverColor());
		} else
			g2d.setColor(Theme.getPanelButtonColor());
		
		g2d.setColor(Color.ORANGE);
//		g2d.fillRect(x, y, width, height);
		g2d.fillRect(0, 0, parent.getWidth(), parent.getHeight());
		g2d.setColor(Theme.getPanelTextColor());
		g2d.drawString(filename, TEXT_LEFT_MARGIN, y + 20);
//		parent.repaint();
	}

//	public boolean containsPoint(Point point) {
//		if (point.y >= y + wheelRotationAmount && point.y <= y + wheelRotationAmount + HEIGHT)
//			return true;
//		else
//			return false;
//	}
}
