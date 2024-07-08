package view.canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import utils.Theme;
import view.Window;

public class EmptySpace extends Component {

	public EmptySpace(Window window) {
		super(window, 0, 0, window.width, window.height);
	}

	public void draw(Graphics2D g2d) {
		// Draw nothing ...
	}

	@Override
	public void update() {
		width = window.width;
		height = window.height;
	}

}
