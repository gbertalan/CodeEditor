package model;

import java.util.ArrayList;

/**
 * The Model class handles a catalog of BoxModels.
 * 
 * @author Gergely Bertalan
 *
 */
public class Model {

	private ArrayList<BoxModel> boxModelCatalog;
	private int noOfBoxModels;

	public Model() {
		boxModelCatalog = new ArrayList<>();
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
