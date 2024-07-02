package view;

import javax.swing.JFrame;

public class View {

	private Window window;

	public View() {
		window = new Window();
		Canvas canvas = new Canvas(window);
//		window.addPanel(new FilePanel(window));
//		window.addPanel(new Panel());
		window.addCanvas(canvas);
		
		window.refresh();
//		printComponents();
	}

	public JFrame getWindowAsJFrame() {
		return window;
	}

	public Window getWindow() {
		return window;
	}

	private void printComponents() {
		int noOfComps = window.getContentPane().getComponentCount();
		for (int i = 0; i < noOfComps; i++) {
			System.out.println((i + 1) + "/" + noOfComps + ": " + "index:"+i+" - "+window.getContentPane().getComponent(i));
		}
	}

}
