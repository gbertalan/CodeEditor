package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utils.Globals;

public class InnerCanvas extends JPanel {

	private Window window;
	
	public InnerCanvas(Window window) {
		this.window = window;
		
		setLayout(null);
		setBounds(10, 10, 700, 300);
		setBackground(Color.RED);
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(30, 30, 600, 300);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);
		
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
	}
}
