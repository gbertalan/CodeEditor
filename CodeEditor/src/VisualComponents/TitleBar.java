package VisualComponents;

import java.awt.Graphics2D;
import main.Window;
import utils.Theme;

public class TitleBar extends Component {
	
	private static int TITLEBAR_HEIGHT = 42;

    public TitleBar(Window window) {
        super(window, 0, 0, window.width, TITLEBAR_HEIGHT);
        this.adjustHeightOnResize = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Theme.getSeparatorLineColor());
        g2d.drawLine(0, height, width, height);
    }
}
