package visual_components;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Window;
import utils.Theme;

public class Background extends Component {
	
	private static int ARC_AMOUNT = 18;

	public Background(Window window) {
		super(window, 0, 0, window.width, window.height);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Theme.getBackgroundColor());
		
		int state = Window.jFrame.getExtendedState();
        if ((state & Window.MAXIMIZED_BOTH) == Window.MAXIMIZED_BOTH) {
        	g2d.fillRect(0, 0, window.width, window.height);
    		g2d.setColor(new Color(69, 69, 69));
    		g2d.drawRect(0, 0, window.width, window.height);
        } else {
        	g2d.fillRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
    		g2d.setColor(new Color(69, 69, 69));
    		g2d.drawRoundRect(0, 0, window.width, window.height, ARC_AMOUNT, ARC_AMOUNT);
        }
	}

	@Override
	public void update() {
		width = window.width;
		height = window.height;	
	}
}
