package view;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class FileBox {

	private String fileName;
	private int x;
	private int y;
	private int width = 300;
	private int height = 400;

	public FileBox(String fileName, int x, int y) {
		this.fileName = fileName;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.fillRect(x, y, width, height);
	}
}
