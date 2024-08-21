package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import view.window.mainUI.component.box.Box;

/**
 * The Model class handles a catalog of BoxModels.
 * 
 * @author Gergely Bertalan
 *
 */
public class Model {

//	private ArrayList<BoxModel> boxModelCatalog;
	private Map<Integer, BoxModel> boxModelCatalog;

	public Model() {
		boxModelCatalog = new HashMap<>();
	}

	public BoxModel createBoxModel(int id, String filename) {
		BoxModel boxModel = new BoxModel(filename);
		boxModelCatalog.put(id, boxModel);
		return boxModel;
	}

	public BoxModel getBoxModel(int id) {
		return boxModelCatalog.get(id);
	}
	
	public BoxModel removeBoxModel(int id) {
		return boxModelCatalog.remove(id);
	}
	
	public int getNoOfBoxModels() {
		return boxModelCatalog.size();
	}
}
