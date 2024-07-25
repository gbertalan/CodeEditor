package view.window.mainUI.component.box;

import java.awt.Graphics2D;

public class DisplayedLine {

	private int lineNumber;
	private StringBuilder lineText;
	private Box box;
	private LineNumberContainer lineNumberContainer;
	private LineTextContainer lineTextContainer;

	public DisplayedLine(Box box, int lineNumber, String lineText, int lineIndex) {
		this.box = box;
		this.lineNumber = lineNumber;
		this.lineText = new StringBuilder(lineText);
		
		lineNumberContainer = new LineNumberContainer(box, lineNumber, lineIndex);
		lineTextContainer = new LineTextContainer(box, lineText, lineNumberContainer);
	}
	
	public void draw(Graphics2D g2d) {
		lineNumberContainer.draw(g2d);
		lineTextContainer.draw(g2d);
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public StringBuilder getLineText() {
		return lineText;
	}

	public void setLineText(StringBuilder lineText) {
		this.lineText = lineText;
	}
	
	public boolean isEmpty() {
        return lineText.length() == 0;
    }

    public void append(String moreText) {
    	lineText.append(moreText);
    }
	
    public Box getBox() {
    	return box;
    }
}
