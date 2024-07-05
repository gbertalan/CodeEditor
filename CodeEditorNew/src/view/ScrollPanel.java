package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

import utils.Globals;
import utils.Theme;

public class ScrollPanel extends JPanel {

	private int x, y, width, height;

	private static Color BACKGROUND_COLOR = Theme.getBackgroundColor();

	public ScrollPanel(int x, int y, int width, int height, ArrayList<String> list) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		setLayout(null);
		setBackground(BACKGROUND_COLOR);
		setBounds(x, y, width, height);

		for (int i = 0; i < list.size(); i++) {
			ScrollButton sb = new ScrollButton(list.get(i), width);
			add(sb);
		}

		ScrollButton.resetCounter();

	}

//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//
//		Graphics2D g2d = (Graphics2D) g;
//		Globals.setRenderingHints(g2d);
//
//		g2d.setColor(getParent().getBackground());
//
//		g2d.fillRect(0, 0, width, HEIGHT);
//
//	}
}
