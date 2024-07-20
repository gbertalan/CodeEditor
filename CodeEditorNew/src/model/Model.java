package model;

import java.util.ArrayList;

public class Model {

    private BoxModel boxModel;
    private ArrayList<BoxModel> boxModelCatalog = new ArrayList<>();

    public Model() {
    }

    public void createBoxModel(String filename) {
        try {
            boxModel = new BoxModel(filename);
            boxModelCatalog.add(boxModel);
        } catch (Exception e) {
            System.err.println("Failed to create BoxModel: " + e.getMessage());
        }
    }

    public BoxModel getBoxModel() {
        if (boxModel == null) {
            throw new IllegalStateException("BoxModel has not been created yet");
        }
        return boxModel;
    }
}
