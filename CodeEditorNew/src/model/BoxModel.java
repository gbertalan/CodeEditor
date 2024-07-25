package model;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxModel {
	private String headerText;
	private ArrayList<String> allLinesList;

	public BoxModel(String filename) {
		headerText = filename;
	}

	public String getHeaderText() {
		return headerText;
	}

	/**
	 * Returns a list of lists of words from the file contents starting from
	 * startIndex to endIndex, inclusive. If startIndex is greater than endIndex, an
	 * IndexOutOfBoundsException is thrown. If allLinesList is null, an empty
	 * ArrayList is returned. If startIndex is less than 0, it is set to 0. If
	 * endIndex is greater than the index of the last element, it is set to the
	 * index of the last element.
	 * 
	 * @param startIndex the starting index, inclusive
	 * @param endIndex   the ending index, inclusive
	 * @return an ArrayList of ArrayLists of words from startIndex to endIndex,
	 *         inclusive
	 */
	public ArrayList<ArrayList<String>> getContentLineList(int startIndex, int endIndex) {
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

		ArrayList<ArrayList<String>> result = new ArrayList<>();

		for (int i = startIndex; i <= endIndex; i++) {
			String line = allLinesList.get(i);
			ArrayList<String> words = new ArrayList<>(Arrays.asList(line.split("\\s+")));
			result.add(words);
		}

		return result;

	}
	
	/**
     * Returns a list of lines from the file contents starting from
     * startIndex to endIndex, inclusive. If startIndex is greater than endIndex, an
     * IndexOutOfBoundsException is thrown. If allLinesList is null, an empty
     * ArrayList is returned. If startIndex is less than 0, it is set to 0. If
     * endIndex is greater than the index of the last element, it is set to the
     * index of the last element.
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

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public void setAllLinesList(ArrayList<String> allLinesList) {
		this.allLinesList = allLinesList;
	}
}
