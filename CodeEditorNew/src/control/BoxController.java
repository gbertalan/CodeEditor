package control;

import model.Model;
import utils.ReadWrite;
import view.View;
import view.window.mainUI.component.box.Box;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		String filename = "something.txt";
		model.createBoxModel(filename);
		Box newBox = new Box(view.getWindow(), 1, 180, 100);
		newBox.createHeader(model.getBoxModel().getHeaderText());
		model.getBoxModel().setAllLinesList(ReadWrite.readFileInResourcesAsArrayList(filename));
		
		newBox.createContent(model.getBoxModel().getContentLineList(0, 3));
		view.getWindow().getMainUI().addComponent(newBox);
		newBox.repaint();
	}

}
