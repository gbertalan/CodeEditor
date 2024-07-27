package control;

import model.Model;
import utils.ReadWrite;
import view.View;
import view.window.mainUI.component.box.Box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoxController {
	private Model model;
	private View view;
	private Control control;
	private Map<Integer, Box> boxMap;
	private int noOfBoxes;
	private int startIndex = 0;
	private int endIndex = 32;

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

		ArrayList<String> readInLines = ReadWrite.readFileInResourcesAsArrayList(filename);
//		ArrayList<String> replacedSpaces = replaceSpacesWithNonBreakingSpaces(readInLines);

		model.getBoxModel().setAllLinesList(readInLines);

		ArrayList<String> linesToDisplay = model.getBoxModel().getFileLineList(startIndex, endIndex);
		newBox.createContent(linesToDisplay, startIndex, endIndex - startIndex, readInLines.size());

		view.getWindow().getMainUI().addComponent(newBox);
		newBox.repaint();
		++noOfBoxes;
	}

	private ArrayList<String> replaceSpacesWithNonBreakingSpaces(ArrayList<String> lines) {
		ArrayList<String> modifiedLines = new ArrayList<>();
		for (String line : lines) {
			// Replace spaces with non-breaking spaces
			String modifiedLine = line.replace(" ", "\u00A0");
			modifiedLines.add(modifiedLine);
		}
		return modifiedLines;
	}

	public void closeBox(Box box) {
		boxMap.remove(box.getId());
		view.getWindow().getMainUI().removeComponent(box);
		box.repaint();
		--noOfBoxes;
	}

	public Map<Integer, Box> getBoxMap() {
		return boxMap;
	}

	public Box getBoxById(int id) {
		return boxMap.get(id);
	}

	public int getNoOfBoxes() {
		return noOfBoxes;
	}

}
