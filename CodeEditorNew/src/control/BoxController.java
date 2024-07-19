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

    

    public void loadHeaderText(String filePath) {
        try {
        	model.getBoxModel().loadHeaderTextFromFile(filePath);
            updateBox(0);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., show an error message in the view
        }
    }
    
    public void updateBox(int boxID) {
    	Box box = view.getWindow().getMainUI().getBoxList().get(boxID); 
		box.getBoxHeader().setHeaderText(model.getBoxModel().getHeaderText());
		box.repaint();
	}

    // Additional methods to handle user interaction
}
