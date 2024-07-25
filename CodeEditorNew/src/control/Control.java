package control;

import model.Model;
import view.View;
import view.window.Listener;
import view.window.mainUI.component.box.Box;

public class Control {

	private Model model;
	private View view;
	private BoxController boxController;

	public Control(Model model, View view) {
		this.model = model;
		this.view = view;
		boxController = new BoxController(model, view, this);
		view.getWindow().getListener().setBoxController(boxController);
	}
	
	public BoxController getBoxController() {
		return boxController;
	}

	

}
