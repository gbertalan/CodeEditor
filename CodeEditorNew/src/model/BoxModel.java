package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Gergely Bertalan
 *
 */
public class BoxModel {
	private String filename;
	private ArrayList<String> textContents;
	private Model model;

	public BoxModel(Model model, int id, String filename, ArrayList<String> textContents) {
		this.model = model;
		this.filename = filename;
		this.textContents = textContents;
		
		model.boxModelCatalog.put(id, this);
	}

	public String getFilename() {
		return filename;
	}
	
	/**
     * Returns a list of lines from the file contents starting from
     * startIndex to endIndex, inclusive.
     * 
     * @param startIndex the starting index, inclusive
     * @param endIndex   the ending index, inclusive
     * @return an ArrayList of lines from startIndex to endIndex,
     *         inclusive
     */
    public ArrayList<String> getFileLineList(int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            throw new IndexOutOfBoundsException("startIndex cannot be greater than endIndex");
        }

        if (textContents == null) {
            return new ArrayList<>();
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (endIndex >= textContents.size()) {
            endIndex = textContents.size() - 1;
        }

        ArrayList<String> result = new ArrayList<>();

        for (int i = startIndex; i <= endIndex; i++) {
            result.add(textContents.get(i));
        }

        return result;
    }

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setTextContents(ArrayList<String> textContents) {
		this.textContents = textContents;
	}
}
