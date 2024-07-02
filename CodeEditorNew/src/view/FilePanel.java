package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import utils.Globals;

public class FilePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static int TOP_MARGIN = 42;
	private static int LEFT_MARGIN = 55;
	private static int BOTTOM_MARGIN = 8;
	private static int WIDTH = 300;

	public boolean hovered = false;

	private Window window;

	public FilePanel(Window window) {
		this.window = window;
		setLayout(null);
		setBounds(LEFT_MARGIN, TOP_MARGIN + 1, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN-1);
		setBackground(Color.YELLOW);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(LEFT_MARGIN, TOP_MARGIN + 1, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN-1);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		// Set font family to match VSCode
		g2d.setFont(new Font("Consolas", Font.PLAIN, 16));

		// Draw the UI elements
		drawButtons(g2d);
		drawFileList(g2d);

	}

	private void drawButtons(Graphics2D g2d) {
		// Button labels
		String[] buttons = { "Create New Project", "Select Existing Project", "Open Working Directory" };

		// Coordinates for buttons
		int x = 50;
		int y = 50;
		int width = 300;
		int height = 40;
		int spacing = 10;

		for (String label : buttons) {
			drawButton(g2d, label, x, y, width, height);
			y += height + spacing;
		}
	}

	private void drawButton(Graphics2D g2d, String label, int x, int y, int width, int height) {
		// Draw button background
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new RoundRectangle2D.Double(x, y, width, height, 10, 10));

		// Draw button border
		g2d.setColor(Color.DARK_GRAY);
		g2d.draw(new RoundRectangle2D.Double(x, y, width, height, 10, 10));

		// Draw button label
		g2d.setColor(Color.BLACK);
		FontMetrics fm = g2d.getFontMetrics();
		int labelX = x + (width - fm.stringWidth(label)) / 2;
		int labelY = y + (height - fm.getHeight()) / 2 + fm.getAscent();
		g2d.drawString(label, labelX, labelY);
	}

	private void drawFileList(Graphics2D g2d) {
		// Sample file list
		List<String> files = Arrays.asList("file1.txt", "file2.txt", "file3.java", "file4.py");

		// Coordinates for file list
		int x = 50;
		int y = 200;
		int spacing = 5;

		for (String file : files) {
			g2d.drawString(file, x, y);
			y += g2d.getFontMetrics().getHeight() + spacing;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		hovered = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		hovered = false;
	}
}
