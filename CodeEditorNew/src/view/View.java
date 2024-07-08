package view;

import javax.swing.JFrame;

import utils.ANSIText;
import view.canvas.Canvas;

public class View {

	private Window window;

	public View() {
		
		System.out.println(ANSIText.bold("View constructor is called."));
		window = new Window();
		
		MainBackgroundPanel mainBackgroundPanel = new MainBackgroundPanel(window);
		InnerCanvas innerCanvas = new InnerCanvas(window);
		Canvas canvas = new Canvas(window);

		window.addMainBackgroundPanel(mainBackgroundPanel);
		window.addInnerCanvas(innerCanvas);
		window.addCanvas(canvas);
		
		window.getContentPane().setComponentZOrder(mainBackgroundPanel, 2);
		window.getContentPane().setComponentZOrder(innerCanvas, 1);
		window.getContentPane().setComponentZOrder(canvas, 0);

		window.refresh();
		printComponents();
		window.getCanvas().update();
		
		System.out.println(ANSIText.bold("View constructor is finished."));
		System.out.println();
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
