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
		g2d.setColor(Theme.getBoxHeaderBackgroundColor());
		g2d.fillRect(box.getLocX(), box.getLocY(), box.getWidth(), box.getHeight() / HEIGHT_DIVISOR);

		g2d.setColor(Theme.getBoxHeaderTextColor());

		Globals.drawVerticallyCenteredText(g2d, box.getLocX(), box.getLocY(), box.getWidth() / TEXT_LEFT_MARGIN_DIVISOR, box.getWidth(), box.getHeight() / HEIGHT_DIVISOR,
				headerText);
		
		g2d.setColor(Theme.getSeparatorLineColor());
		g2d.drawRect(box.getLocX(), box.getLocY(), box.getWidth(), box.getHeight() / HEIGHT_DIVISOR);
	}
	
	public int getHeight() {
		return box.getHeight() / HEIGHT_DIVISOR;
	}

}
