package control;

import model.Model;
import view.View;
import view.window.Listener;

public class Control {

	private Model model;
    private View view;

    public Control(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    
}
