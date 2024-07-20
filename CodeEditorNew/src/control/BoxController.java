package control;

import model.Model;
import view.View;
import view.window.mainUI.component.box.Box;

import java.io.IOException;

public class BoxController {
    private Model model;
    private View view;
    private Control control;

    public BoxController(Model model, View view, Control control) {
        this.model = model;
        this.view = view;
        this.control = control;
    }

    public void createBox() {
    	Box newBox = new Box(view.getWindow(), 1, 180, 100, model.getBoxModel().getHeaderText());
    	view.getWindow().getMainUI().addComponent(newBox);
    }

}
