package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import utils.Globals;
import utils.Theme;

public class ScrollableListPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	
	private int x, y, width, height;
	private ArrayList<ScrollableButton> buttonList = new ArrayList<>();
	private Object hoveredFileComponent = this;

	public ScrollableListPanel(int x, int y, int width, int height, ArrayList<String> list) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		for (int i = 0; i < list.size(); i++) {
			buttonList.add(new ScrollableButton(i, list.get(i), this));
		}
		
		setLayout(null);
		setBounds(x, y, width, height);
//		setBackground(Theme.getOpenedPanelColor());
		setBackground(Color.RED);

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(x, y, width, height);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

//		g2d.setColor(Theme.getOpenedPanelColor());
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).draw(hoveredFileComponent, g2d);
		}

//		g2d.dispose();
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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
	}

}
