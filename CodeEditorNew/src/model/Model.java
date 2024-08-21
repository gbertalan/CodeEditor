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
		BoxModel boxModel = new BoxModel(noOfBoxModels++, filename);
		boxModelCatalog.add(boxModel);
		return boxModel;
	}

	public BoxModel getBoxModel(int id) {
		return boxModelCatalog.get(id);
	}
}
