package VisualComponents;

import java.awt.Graphics2D;

import main.Window;
import utils.Theme;

public class CloseButton extends Component {

	private static int SIZE = 42;
	private static int ARC_AMOUNT = 18;

	public CloseButton(Window window) {
		super(window, window.width - SIZE, 0, SIZE, SIZE);
//		this.adjustHeightOnResize = false;
//		this.adjustWidthOnResize = true;
	}

	@Override
	public void draw(Graphics2D g2d) {
		System.out.println("width:"+ width);
		g2d.setColor(Theme.getDEBUGColor());
		g2d.fillRoundRect(locX, locY, width, height, ARC_AMOUNT, ARC_AMOUNT);
	}
	
	@Override
	public void updateLocX() {
		locX = window.width - SIZE;
	}

}
