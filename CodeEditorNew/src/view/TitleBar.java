package view;

import java.awt.Graphics2D;

import utils.Theme;

public class TitleBar extends Component {

	private static int TITLEBAR_HEIGHT = 42;

	public TitleBar(Window window) {
		super(window, 0, 0, window.width, TITLEBAR_HEIGHT);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawLine(0, TITLEBAR_HEIGHT, window.width, TITLEBAR_HEIGHT);
	}

	@Override
	public void update() {
		width = window.width;
	}
}
