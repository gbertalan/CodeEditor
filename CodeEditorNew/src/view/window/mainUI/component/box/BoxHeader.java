package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Globals;
import utils.Theme;

public class BoxHeader implements BoxComponent{

	private static int HEIGHT_DIVISOR = 16;
	private static int TEXT_LEFT_MARGIN_DIVISOR = 20;

	private String headerText;
	private Box box;

	public BoxHeader(Box box, String headerText) {
		this.box = box;
		this.headerText = headerText;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(box.getLocX(), box.getLocY(), box.getWidth(), box.getHeight() / HEIGHT_DIVISOR);

		g2d.setColor(Color.BLUE);

		Globals.drawCenteredText(g2d, box.getLocX(), box.getLocY(), box.getWidth() / TEXT_LEFT_MARGIN_DIVISOR, box.getWidth(), box.getHeight() / HEIGHT_DIVISOR,
				headerText);
	}
	
	public int getHeight() {
		return box.getHeight() / HEIGHT_DIVISOR;
	}

}
