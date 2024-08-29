package control;

import model.BoxModel;
import model.Model;
import utils.ReadWrite;
import view.View;
import view.window.mainUI.component.box.Box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The BoxController class controls the boxes, individually.
 * Main functions:
 * - creates a new box
 * - updates the contents of a given box
 * - closes a given box
 * @author Gergely Bertalan
 *
 */
public class BoxController {
	private Model model;
	private View view;
	private Map<Integer, Box> boxMap;
	private ArrayList<String> readInLines;
	private ArrayList<String> linesToDisplay;
	
	private static int NO_OF_DISPLAYED_LINES = 32;

	public BoxController(Model model, View view, Control control) {
		this.model = model;
		this.view = view;
		this.boxMap = new HashMap<>();
	}

	public void createBox(String filename, String pathWithFilename, int locX, int locY) {

		Box newBox = new Box(view.getWindow(), 1, locX, locY, 830, 800, this);
		
		readInLines = ReadWrite.readFileInPathAsArrayList(pathWithFilename);
		BoxModel boxModel = new BoxModel(model, newBox.getId(), filename, readInLines);

		boxMap.put(newBox.getId(), newBox);
		newBox.createHeader(filename);

		int startIndex = 0;
		int endIndex = startIndex + NO_OF_DISPLAYED_LINES;
		linesToDisplay = boxModel.getFileLineList(startIndex, endIndex);
		newBox.createContent(linesToDisplay, startIndex, endIndex - startIndex + 1, readInLines.size());

		view.getWindow().getMainUI().addComponent(newBox);
		newBox.repaint();
	}

	public void updateBoxContent(Box box, int startIndex) {
		int endIndex = startIndex + NO_OF_DISPLAYED_LINES;
		linesToDisplay = model.getBoxModel(box.getId()).getFileLineList(startIndex, endIndex);
		box.updateContent(startIndex, linesToDisplay);
	}

	public void closeBox(Box box) {
		boxMap.remove(box.getId());
		model.removeBoxModel(box.getId());
		view.getWindow().getMainUI().removeComponent(box);
		box.repaint();
	}

	public Map<Integer, Box> getBoxMap() {
		return boxMap;
	}

	public Box getBoxById(int id) {
		return boxMap.get(id);
	}

	public int getNoOfBoxes() {
		return boxMap.size();
	}

}
