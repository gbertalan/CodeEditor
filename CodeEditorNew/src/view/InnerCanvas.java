package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import utils.Globals;

public class InnerCanvas extends JPanel {

	private Window window;
	private static int MARGIN = 2;

	public InnerCanvas(Window window) {
		this.window = window;

		setLayout(null);
		setBounds(MARGIN, MARGIN, window.width - (MARGIN * 2), window.height - (MARGIN * 2));
//		setBackground(Color.RED);
		setOpaque(false);

		FileBox fileBox = new FileBox();
		add(fileBox);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(MARGIN, MARGIN, window.width - (MARGIN * 2), window.height - (MARGIN * 2));

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		g2d.setColor(new Color(255, 255, 0, 130));
		g2d.fillRect(0, 0, getWidth(), getHeight());

	}
}
