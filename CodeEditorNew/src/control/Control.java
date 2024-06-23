package control;

import model.Model;
import view.View;

public class Control {

	private Model model;
    private View view;

    public Control(Model model, View view) {
        this.model = model;
        this.view = view;

        WindowListener windowListener = new WindowListener(view.getWindow());
        InnerListener innerListener = new InnerListener(view.getWindow());
    }
    
}
