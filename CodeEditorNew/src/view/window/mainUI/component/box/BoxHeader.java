package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Globals;
import utils.Theme;

public class BoxHeader {

	private static int HEIGHT_DIVISOR = 16;
	private static int TEXT_LEFT_MARGIN_DIVISOR = 20;

//	private String headerText = "proba.szoveg";
	private String headerText;

	public BoxHeader(String headerText) {
		this.headerText = headerText;
	}

	public void draw(Graphics2D g2d, int boxLocX, int boxLocY, int boxWidth, int boxHeight) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(boxLocX, boxLocY, boxWidth, boxHeight / HEIGHT_DIVISOR);

		g2d.setColor(Color.BLUE);
//		g2d.drawString(headerText, boxLocX + boxWidth / TEXT_LEFT_MARGIN_DIVISOR, boxLocY
//				+ Globals.centerTextVert(g2d, headerText, Theme.getBoxHeaderFont(), boxHeight / HEIGHT_DIVISOR));

		Globals.drawCenteredText(g2d, boxLocX, boxLocY, boxWidth / TEXT_LEFT_MARGIN_DIVISOR, boxWidth, boxHeight / HEIGHT_DIVISOR,
				headerText);
	}

}
