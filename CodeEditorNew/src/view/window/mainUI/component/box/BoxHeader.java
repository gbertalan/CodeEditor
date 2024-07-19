package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

public class BoxHeader {

	private static int HEIGHT_DIVISOR = 8;
	
	private String headerText;

	public BoxHeader() {

	}

	public void draw(Graphics2D g2d, int boxLocX, int boxLocY, int boxWidth, int boxHeight) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(boxLocX, boxLocY, boxWidth, boxHeight / HEIGHT_DIVISOR);
	}
	
	public void setHeaderText(String headerText) {
		// TODO Auto-generated method stub
		this.headerText = headerText;
	}
}
