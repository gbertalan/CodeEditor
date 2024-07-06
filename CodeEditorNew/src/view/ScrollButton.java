package view;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.WindowListener;
import utils.Globals;
import utils.Theme;

public class ScrollButton extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	private Window window;
	private ScrollPanel parent;
	private int width;
	private static int HEIGHT = ScrollPanel.BUTTON_HEIGHT;
	private static final int TEXT_LEFT_MARGIN = 30;
	private static final int TEXT_TOP_MARGIN = 21;

	private String text;
	private static int buttonCounter = 0;

	private boolean entered;

	private int ID;

	public ScrollButton(Window window, String text, ScrollPanel parent) {

		this.window = window;
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

		g2d.setFont(new Font("Verdana", Font.PLAIN, 14));

		g2d.setColor(Theme.getPanelTextColor());
		g2d.drawString(text, TEXT_LEFT_MARGIN, TEXT_TOP_MARGIN);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		window.getInnerCanvas()
//				.addFileBox(new FileBox(
//						window, this.text, parent.getParent().getX() + parent.getX(), parent.getParent().getY()
//								+ parent.getY() + ((ID - 1) * (HEIGHT - 2)) + ScrollPanel.savedScrollAmount,
//						e.getX(), e.getY(), true));

//		window.getInnerCanvas().addFileBox(new FileBox(window, this.text, e.getX(), e.getY(), true));
//		window.getCanvas().update();
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
		System.out.println("dragging");

		try {
			Robot robot = new Robot();
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
		window.getCanvas().update();
		window.getInnerCanvas().addFileBox(new FileBox(window, this.text, e.getX(), e.getY(), true));
		try {
			Robot robot = new Robot();
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
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
