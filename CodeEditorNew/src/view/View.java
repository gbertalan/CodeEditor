package view;

import view.window.Window;

public class View {

	private Window window;

	public View() {
		this.window = new Window();
	}

	public Window getWindow() {
		return this.window;
	}
}
