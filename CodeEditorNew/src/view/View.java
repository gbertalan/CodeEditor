package view;

import javax.swing.JFrame;

import utils.ANSIText;
import view.window.mainUI.MainUI;
import view.window.Listener;
import view.window.Window;
import view.window.workspace.Workspace;

public class View {

	private Window window;

	public View() {
		System.out.println(ANSIText.bold("View constructor is called."));
		
		initWindow();

//		window.refresh();
//		printComponents(window);
//		window.getMainUI().update();

		System.out.println(ANSIText.bold("View constructor is finished."));
		System.out.println();
	}
	
	private void initWindow() {
		window = new Window();

		window.attachPanels();
		window.attachListeners();
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
