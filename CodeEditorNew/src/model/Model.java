package model;

import java.util.ArrayList;

public class Model {

	private ArrayList<BoxModel> boxModelCatalog = new ArrayList<>();
	private int noOfBoxModels;

	public Model() {
	}

	public BoxModel createBoxModel(String filename) {
		BoxModel boxModel;
		try {
			boxModel = new BoxModel(noOfBoxModels++, filename);
			boxModelCatalog.add(boxModel);
			return boxModel;
		} catch (Exception e) {
			System.err.println("Failed to create BoxModel: " + e.getMessage());
			return null;
		}

	}

	public BoxModel getBoxModel(int id) {
		return boxModelCatalog.get(id);
	}
}
