package model;

import java.util.ArrayList;

public class BoxModel {
    private String headerText;
    private ArrayList<String> contentLineList;

    public BoxModel() {
    	headerText = "header text not set";
    }

    public String getHeaderText() {
        return headerText;
    }
    
    /**
     * Returns a fix number of lines from the file 
     * @return 
     */
    public ArrayList<String> getContentLineList() {
    	return contentLineList;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }
    
    
}


