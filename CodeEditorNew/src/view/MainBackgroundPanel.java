package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import utils.Globals;
import utils.Theme;

public class MainBackgroundPanel extends JPanel {

	private static int ARC_AMOUNT = 18;

	private Window window;

	private boolean hidden;
	private boolean drawable = true;

	public MainBackgroundPanel(Window window) {
		this.window = window;

		setLayout(null);
		setBounds(0, 0, window.width, window.height);
		setBackground(new Color(0, 250, 0, 0));
		setOpaque(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!hidden) {
//			if (drawable) {
				setBounds(0, 0, window.width, window.height);

				Graphics2D g2d = (Graphics2D) g;
				Globals.setRenderingHints(g2d);

				g2d.setColor(Color.GREEN);
				g2d.fillRect(50, 50, 400, 400);

				g2d.setColor(Theme.getBackgroundColor());
				g2d.setColor(new Color(130, 30, 30));

				if (window.isMaximized()) {
					g2d.fillRect(0, 0, window.width, window.height);
					g2d.setColor(Theme.getSeparatorLineColor());
					g2d.drawRect(0, 0, window.width, window.height);
				} else {
					g2d.fillRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
					g2d.setColor(Theme.getSeparatorLineColor());
					g2d.drawRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
				}
				drawable = false;
//			}
		}

	}

	public void update() {
		drawable = true;
		repaint();
		System.out.println("MB repaint()");
	}

	public void hide() {
		this.hidden = true;
	}

	public void unHide() {
		this.hidden = false;
	}
}
