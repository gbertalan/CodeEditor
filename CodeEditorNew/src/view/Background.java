package view;

import java.awt.Graphics2D;
import utils.Theme;

public class Background extends Component{

	private static int ARC_AMOUNT = 18;

	public Background(Window window) {
		super(window, 0, 0, window.width, window.height);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBackgroundColor());

		if (window.isMaximized()) {
			g2d.fillRect(0, 0, window.width, window.height);
			g2d.setColor(Theme.getSeparatorLineColor());
			g2d.drawRect(0, 0, window.width, window.height);
		} else {
			g2d.fillRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
			g2d.setColor(Theme.getSeparatorLineColor());
			g2d.drawRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
		}
	}

	@Override
	public void update() {
		width = window.width;
		height = window.height;	
	}

}
