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
	private ArrayList<String> allLinesList;

	public BoxModel(int ID, String filename) {
		this.filename = filename;
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

        if (allLinesList == null) {
            return new ArrayList<>();
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        if (endIndex >= allLinesList.size()) {
            endIndex = allLinesList.size() - 1;
        }

        ArrayList<String> result = new ArrayList<>();

        for (int i = startIndex; i <= endIndex; i++) {
            result.add(allLinesList.get(i));
        }

        return result;
    }

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setAllLinesList(ArrayList<String> allLinesList) {
		this.allLinesList = allLinesList;
	}
}
