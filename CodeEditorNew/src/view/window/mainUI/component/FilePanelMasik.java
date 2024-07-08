package view.window.mainUI.component;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import view.window.Window;

public class FilePanelMasik extends UIComponent{

	public FilePanelMasik(Window window) {
		super(window, 300, 300, 600, 600);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(locX, locY, width, height);
	}

}
