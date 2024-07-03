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
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import utils.Globals;
import utils.Theme;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SettingsPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static int TOP_MARGIN = 42;
	private static int LEFT_MARGIN = 55;
	private static int BOTTOM_MARGIN = 8;
	private static int WIDTH = 300;
	private static int HEADER_HEIGHT = 24;

	public boolean hovered = false;

	private Window window;
	private BufferedImage image;
	private JTextField textField;

	public SettingsPanel(Window window) {
		this.window = window;
		setLayout(null);
		setBounds(LEFT_MARGIN, TOP_MARGIN, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN - 1);
		setBackground(Theme.getOpenedPanelColor());

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBounds(LEFT_MARGIN, TOP_MARGIN, WIDTH, window.height - TOP_MARGIN - BOTTOM_MARGIN - 1);

		Graphics2D g2d = (Graphics2D) g;
		Globals.setRenderingHints(g2d);

		g2d.setColor(Theme.getPanelHeaderTextColor());
		drawText(g2d, "SETTINGS", true, 0, HEADER_HEIGHT, Font.BOLD, 16);

		drawSeparatorLine(g2d, 36);

		// Draw the border
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(0, 0, getWidth(), getHeight());
	}

	private void drawSeparatorLine(Graphics2D g2d, int separationY) {
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(0, separationY, getWidth(), separationY);
	}

	private void drawButton(Graphics2D g2d, boolean centered, int x, int y, int width, int height, String text,
			BufferedImage image) {

		int arc = 8;

		if (centered) {
			x = (WIDTH - width) / 2;
		}

		g2d.setColor(Theme.getPanelButtonColor());
		g2d.fillRoundRect(x, y, width, height, arc, arc);
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRoundRect(x, y, width, height, arc, arc);

		g2d.setColor(Theme.getPanelTextColor());
		g2d.setFont(new Font("Consolas", Font.BOLD, 14));
		FontMetrics fm = g2d.getFontMetrics();
		int textWidth = fm.stringWidth(text);
		int textHeight = fm.getAscent();
		g2d.drawString(text, x + (width / 2) - (textWidth / 2), y + (height / 2) + (textHeight / 2));
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
