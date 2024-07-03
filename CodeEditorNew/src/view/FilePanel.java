package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import utils.Globals;
import utils.Theme;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FilePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static int TOP_MARGIN = 42;
	private static int LEFT_MARGIN = 55;
	private static int BOTTOM_MARGIN = 8;
	private static int WIDTH = 300;
	private static int HEADER_HEIGHT = 24;
	private static int COVER_PANEL_HEIGHT = 136;

	public boolean hovered = false;

	private Window window;

	private Graphics2D g2d;
	private PanelButton projectButton;
	private PanelButton folderButton;

	private Object hoveredComponent = this;
	private Object hoveredFileComponent = this;

	ArrayList<String> filenameList = new ArrayList<>();
	ArrayList<FButton> fileList = new ArrayList<>();

	public FilePanel(Window window) {
		this.window = window;
		setLayout(null);
		setBounds(LEFT_MARGIN, TOP_MARGIN, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN - 1);
		setBackground(Theme.getOpenedPanelColor());

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
		filenameList.add("file8");
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

		for (int i = 0; i < filenameList.size(); i++) {
			fileList.add(new FButton(filenameList.get(i), COVER_PANEL_HEIGHT + (30 * i), this));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(LEFT_MARGIN, TOP_MARGIN, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN - 1);

		g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		// background:
		g2d.setColor(Theme.getOpenedPanelColor());
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// scrollable files:
//		FButton fButton = new FButton();
//		fButton.draw(fButton, g2d, this, COVER_PANEL_HEIGHT, filenameList.get(0));
		for (int i = 0; i < fileList.size(); i++) {
			fileList.get(i).draw(hoveredFileComponent, g2d);
		}

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

		g2d.dispose();
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

		// same as previous, but with file list:
		// (a possible simplification is that in the above code projectButton and
		// folderButton could be placed in an ArrayList, similarly to these below. And then maybe the first half of this method can be combines with the second half.)
		// (I could also do that I wouldn't have separate PanelButton and FButton, instead only one of those.)
		for (int i = 0; i < fileList.size(); i++) {
			if (hoveredFileComponent != fileList.get(i) && fileList.get(i).containsPoint(e.getPoint())) {
				hoveredFileComponent = fileList.get(i);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				repaint();
			}
		}

		if (hoveredFileComponent != null) {

			boolean found = false;
			for (int i = 0; i < fileList.size(); i++) {
				if (fileList.get(i).containsPoint(e.getPoint())) {
					found = true;
					break;
				}
			}
			if (!found) {
				hoveredFileComponent = null;
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				repaint();
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (hoveredComponent == projectButton) {
			System.out.println("proj clicked");
		} else if (hoveredComponent == folderButton) {
			System.out.println("fold clicked");
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
	}
}
