package view;

import javax.swing.JFrame;

public class View {

	private Window window;

	public View() {
		window = new Window();
		Canvas canvas = new Canvas(window);
		window.addCanvas(canvas);
	}

	public JFrame getWindowAsJFrame() {
		return window;
	}
	
	public Window getWindow() {
		return window;
	}

}
