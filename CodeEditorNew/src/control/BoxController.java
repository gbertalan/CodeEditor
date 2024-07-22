package control;

import model.Model;
import utils.ReadWrite;
import view.View;
import view.window.mainUI.component.box.Box;

import java.util.HashMap;
import java.util.Map;

public class BoxController {
	private Model model;
	private View view;
	private Control control;
	private Map<Integer, Box> boxMap;
	private int noOfBoxes;

	public BoxController(Model model, View view, Control control) {
		this.model = model;
		this.view = view;
		this.control = control;
		this.boxMap = new HashMap<>();
	}

	public void createBox() {
		String filename = "something.txt";
		model.createBoxModel(filename);
		
		Box newBox = new Box(view.getWindow(), 1, 180, 100, this);
		
		boxMap.put(newBox.getId(), newBox);
		newBox.createHeader(model.getBoxModel().getHeaderText());
		model.getBoxModel().setAllLinesList(ReadWrite.readFileInResourcesAsArrayList(filename));
		
		newBox.createContent(model.getBoxModel().getContentLineList(0, 10));
		view.getWindow().getMainUI().addComponent(newBox);
		newBox.repaint();
		++noOfBoxes;
	}

	public void closeBox(Box box) {
		boxMap.remove(box.getId());
		view.getWindow().getMainUI().removeComponent(box);
		box.repaint();
		--noOfBoxes;
	}
	
	public Box getBoxById(int id) {
		return boxMap.get(id);
	}
	
	public int getNoOfBoxes() {
		return noOfBoxes;
	}
}
