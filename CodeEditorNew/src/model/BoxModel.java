package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BoxModel {
    private String headerText;

    public BoxModel() {
        // Initial header text can be set here if needed
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
        // Notify observers if needed
    }

    // Method to read header text from a file
    public void loadHeaderTextFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        try {
            this.headerText = reader.readLine();
        } finally {
            reader.close();
        }
    }
}


