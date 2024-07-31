package control;

import model.BoxModel;
import model.Model;
import utils.ReadWrite;
import view.View;
import view.window.MouseWheelListener;
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
	private ArrayList<String> readInLines;
	private ArrayList<String> linesToDisplay;

	public BoxController(Model model, View view, Control control) {
		this.model = model;
		this.view = view;
		this.control = control;
		this.boxMap = new HashMap<>();
	}

	public void createBox(String filename, String pathWithFilename, int locX, int locY) {
//		String filename = "something.txt";
		BoxModel boxModel = model.createBoxModel(filename);

		Box newBox = new Box(view.getWindow(), 1, locX, locY, this);

		boxMap.put(newBox.getId(), newBox);
		newBox.createHeader(boxModel.getHeaderText());

//		readInLines = ReadWrite.readFileInResourcesAsArrayList(filename);
		readInLines = ReadWrite.readFileInPathAsArrayList(pathWithFilename);

		boxModel.setAllLinesList(readInLines);

		int startIndex = 0;
		int endIndex = startIndex + 32;

		linesToDisplay = boxModel.getFileLineList(startIndex, endIndex);
		newBox.createContent(linesToDisplay, startIndex, endIndex - startIndex + 1, readInLines.size());

		view.getWindow().getMainUI().addComponent(newBox);
		newBox.repaint();
		++noOfBoxes;
	}

	public void updateContent(Box box, int startIndex) {
		int endIndex = startIndex + 32;
		linesToDisplay = model.getBoxModel(box.getId()).getFileLineList(startIndex, endIndex);
		box.updateContent(startIndex, linesToDisplay);
	
		
//		int newScrollHor = (int)Math.round(box.getScrollHorizontal()*MouseWheelListener.zoomValue);
//		box.setScrollHorizontal(newScrollHor);
		
//		Lekerem a scrollHor-t.
//		Kiszamolom, h a box-hoz kepest mekkora.
//		megszorzom a zoom-mal.
//		visszaallitom.
		// De kelleni fog talan a box eredeti merete is.
		// Ha meg sehogy nemtudom megoldani, akkor hardcode-olom.
		// De most ugy gondolom, h megoldhato ugy, ahogy leirtam itt.
		
		int boxWidth = box.getWidth();
		
		
//		box.setScrollHorizontal(0);
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
