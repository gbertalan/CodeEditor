package VisualComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Window;
import utils.Theme;

public class RoundedBackground extends Component {
	
	private static int ARC_AMOUNT = 18;

	public RoundedBackground(Window window) {
		super(window, 0, 0, window.width, window.height);
//		this.adjustHeightOnResize = true;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBackgroundColor());
		g2d.fillRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
		g2d.setColor(new Color(69, 69, 69));
		g2d.drawRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
	}

	@Override
	public void updateLocX() {
		// TODO Auto-generated method stub
		
	}

}
