package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import utils.Globals;

public class BoxContent implements BoxComponent{

	private Box box;
	private ArrayList<String> contentLineList;
	
	public BoxContent(Box box, ArrayList<String> contentLineList) {
		this.box = box;
		this.contentLineList = contentLineList;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.YELLOW);
		int locX = box.getLocX();
		int locY = box.getLocY()+box.getBoxHeader().getHeight();
		int width = box.getWidth();
		int height = box.getHeight() - box.getBoxHeader().getHeight();
		g2d.fillRect(locX, locY, width, height);
		
		
	}

}
