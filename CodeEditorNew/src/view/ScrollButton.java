package view;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.Globals;
import utils.Theme;

public class ScrollButton extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	private ScrollPanel parent;
	private int width;
	private static int HEIGHT = ScrollPanel.BUTTON_HEIGHT;
	private static final int TEXT_LEFT_MARGIN = 30;

	private String text;
	private static int buttonCounter = 0;

	private boolean entered;

	private int ID;

	public ScrollButton(String text, ScrollPanel parent) {

		this.text = text;
		this.parent = parent;
		this.width = parent.getWidth();

		setLayout(null);
		setBounds(0, buttonCounter * (HEIGHT - 2), width, HEIGHT);
		setBorder(BorderFactory.createEmptyBorder());

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);

		++buttonCounter;
		this.ID = buttonCounter;
	}

	public static void resetCounter() {
		buttonCounter = 0;
	}

	public void scroll(int amount) {
		setLocation(getX(), getY() + amount);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		if (entered)
			g2d.setColor(Theme.getPanelButtonHoverColor());
		else
			g2d.setColor(getParent().getBackground());

		g2d.fillRect(0, 0, width, HEIGHT);

		g2d.setColor(Theme.getPanelTextColor());
		g2d.drawString(text, TEXT_LEFT_MARGIN, 20);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(ID);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		entered = true;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		parent.repaintAllButtons();
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		entered = false;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		parent.repaintAllButtons();
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
		parent.mouseWheelMoved(e);
	}
}
