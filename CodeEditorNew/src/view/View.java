package view;

import utils.ANSIText;
import view.window.Window;

public class View {

	private Window window;

	public View() {
		System.out.println(ANSIText.bold("View constructor is called."));
		
		initWindow();

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
}
