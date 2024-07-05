package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import utils.Globals;
import utils.Theme;

public class ScrollButton extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	private int width;
	private static int HEIGHT = 30;
	private static final int TEXT_LEFT_MARGIN = 30;
	
	private String text;
	private static int buttonCounter = 0;

	private boolean entered;

	public ScrollButton(String text, int width) {

		this.text = text;
		this.width = width; 

		setLayout(null);
		setBounds(0, buttonCounter * (HEIGHT - 1), width, HEIGHT);

		setBackground(Color.CYAN);

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);

		++buttonCounter;
	}
	
	public static void resetCounter() {
		buttonCounter = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		if (entered)
			g2d.setColor(Color.GREEN);
		else
			g2d.setColor(Color.PINK);
		
		g2d.fillRect(0, 0, width, HEIGHT);
		
		g2d.setColor(Theme.getPanelTextColor());
		g2d.drawString(text, TEXT_LEFT_MARGIN, 20);
		
//		g2d.dispose();

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
		entered = true;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		entered = false;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		repaint();
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
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}

}
