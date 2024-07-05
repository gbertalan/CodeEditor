package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utils.Globals;

public class FileButtonNew extends JPanel {

	private static int TOP_MARGIN = 42;
	private static int SIZE = 55;
	
	public FileButtonNew() {
		setLayout(null);
		setBounds(0, TOP_MARGIN+10, SIZE, SIZE);
		setBackground(Color.ORANGE);
		setOpaque(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(0, 0, 100, 100);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);
		
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, 40, 40);
	}
}
