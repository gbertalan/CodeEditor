package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
import java.awt.Color;

public class FilePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static int TOP_MARGIN = 42;
	private static int LEFT_MARGIN = 55;
	private static int BOTTOM_MARGIN = 8;
	private static int WIDTH = 300;
	private static int HEADER_HEIGHT = 24;
	private static int COVER_PANEL_HEIGHT = 138;

	public boolean hovered = false;

	private Window window;
	private int height;

	private Graphics2D g2d;
	private PanelButton projectButton;
	private PanelButton folderButton;

	private Object hoveredComponent = this;

	ArrayList<String> filenameList = new ArrayList<>();

	public ScrollPanel scrollPanel;

	public FilePanel(Window window) {
		this.window = window;
		this.height = window.height - TOP_MARGIN - BOTTOM_MARGIN - 1;
		setLayout(null);
		setBounds(LEFT_MARGIN, TOP_MARGIN, WIDTH, height);
//		setBounds(LEFT_MARGIN, TOP_MARGIN, 300, 549);
		setBackground(Theme.getOpenedPanelColor());
//		setBackground(Color.CYAN);

		projectButton = new PanelButton("PROJECT", 46, this);
		folderButton = new PanelButton("FOLDER", 96, this);

		addMouseListener(this);
		addMouseMotionListener(this);

		filenameList.add("file0.cpp");
		filenameList.add("file1");
		filenameList.add("file2");
		filenameList.add("file3");
		filenameList.add("file4");
		filenameList.add("file5");
		filenameList.add("file6");
		filenameList.add("file7");
		filenameList.add("main.s");
		filenameList.add("file9");
		filenameList.add("file10");
		filenameList.add("file11");
		filenameList.add("file12");
		filenameList.add("file13");
		filenameList.add("file14");
		filenameList.add("file15");
		filenameList.add("file16");
		filenameList.add("file17");
		filenameList.add("file18");
		filenameList.add("file19");
		filenameList.add("file20");
		filenameList.add("file21");
		filenameList.add("file22");
		filenameList.add("file23");
		filenameList.add("file24");
		filenameList.add("file25");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		height = window.height - TOP_MARGIN - BOTTOM_MARGIN - 1;
		setBounds(LEFT_MARGIN, TOP_MARGIN, WIDTH, height);

		g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		// background:
		g2d.setColor(Theme.getOpenedPanelColor());
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// cover panel so that the scrolled text will be covered:
		g2d.setColor(Theme.getFilePanelCoverColor());
		g2d.fillRect(0, 0, WIDTH, COVER_PANEL_HEIGHT);

		// title:
		g2d.setColor(Theme.getPanelHeaderTextColor());
		drawText(g2d, "FILE", true, 0, HEADER_HEIGHT, Font.BOLD, 16);
		g2d.setColor(Theme.getPanelTextColor());

		// panel buttons:
		projectButton.draw(hoveredComponent, g2d);
		folderButton.draw(hoveredComponent, g2d);

		// separators:
		drawSeparatorLine(g2d, 36);
		drawSeparatorLine(g2d, 86);

		// border:
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(0, 0, getWidth(), getHeight());

//		g2d.dispose();
	}

	private void drawSeparatorLine(Graphics2D g2d, int separationY) {
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(0, separationY, getWidth(), separationY);
	}

	/**
	 * Draws a text. If centered is true, the value of x does not matter.
	 * 
	 * @param g2d      - Graphics2D
	 * @param text     - a String to draw
	 * @param centered - if true, the text will be horizontally centered in its
	 *                 containing component
	 * @param x        - the x position
	 * @param y        - the y position
	 * @param fontType - e.g. Font.BOLD
	 * @param fontSize - the point size of the font
	 */
	private void drawText(Graphics2D g2d, String text, boolean centered, int x, int y, int fontType, int fontSize) {

		g2d.setFont(new Font("Consolas", fontType, fontSize));

		if (centered) {
			FontMetrics fm = g2d.getFontMetrics();
			int textWidth = fm.stringWidth(text);
			x = (getWidth() - textWidth) / 2;
		}
		g2d.drawString(text, x, y);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		if (hoveredComponent != projectButton && projectButton.containsPoint(e.getPoint())) {
			hoveredComponent = projectButton;
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			repaint();
		} else if (hoveredComponent != folderButton && folderButton.containsPoint(e.getPoint())) {
			hoveredComponent = folderButton;
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			repaint();
		}

		if (hoveredComponent != null && !projectButton.containsPoint(e.getPoint())
				&& !folderButton.containsPoint(e.getPoint())) {
			hoveredComponent = null;
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (hoveredComponent == projectButton) {
			System.out.println("proj clicked");
		} else if (hoveredComponent == folderButton) {
			System.out.println("fold clicked");

			recreateScrollPanel(window.height);

			revalidate();
			repaint();
		}

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
		hoveredComponent = null;
		repaint();
	}

	public void recreateScrollPanel(int windowHeight) {
		if (scrollPanel != null) {
			remove(scrollPanel);
		}
		createScrollPanel(windowHeight);

	}

	public void removeScrollPanel() {
		if (scrollPanel != null) {
			remove(scrollPanel);
			revalidate();
			repaint();
		}
	}

	private void createScrollPanel(int newHeight) {
		scrollPanel = new ScrollPanel(1, COVER_PANEL_HEIGHT, getWidth() - 2, newHeight - COVER_PANEL_HEIGHT,
				filenameList);
		add(scrollPanel);
		revalidate();
		repaint();
	}
}
