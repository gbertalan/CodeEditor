package view;

import javax.swing.JFrame;

public class View {

	private Window window;

	public View() {
		window = new Window();
		
		Canvas canvas = new Canvas(window);
		MainBackgroundPanel mainBackgroundPanel = new MainBackgroundPanel(window);
		InnerCanvas innerCanvas = new InnerCanvas(window);

		window.addMainBackgroundPanel(mainBackgroundPanel);
		window.addInnerCanvas(innerCanvas);
		window.addCanvas(canvas);
		
		window.getContentPane().setComponentZOrder(mainBackgroundPanel, 2);
		window.getContentPane().setComponentZOrder(innerCanvas, 1);
		window.getContentPane().setComponentZOrder(canvas, 0);

		window.refresh();
		printComponents();
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
			System.out.println(
					(i + 1) + "/" + noOfComps + ": " + "index:" + i + " - " + window.getContentPane().getComponent(i));
		}
	}

}
