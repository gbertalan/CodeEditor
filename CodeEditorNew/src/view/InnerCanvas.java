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
		setBounds(10, 10, 700, 500);
		setBackground(Color.RED);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(0, 0, window.width, window.height);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);
		
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
	}
}
