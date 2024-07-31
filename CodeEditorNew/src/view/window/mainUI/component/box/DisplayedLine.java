package view.window.mainUI.component.box;

import java.awt.Graphics2D;

import utils.ANSIText;

public class DisplayedLine {

	private LineNumberContainer lineNumberContainer;
	private LineTextContainer lineTextContainer;

	public DisplayedLine(Box box, int lineNumber, String lineText, int lineIndex) {
		lineNumberContainer = new LineNumberContainer(box, lineNumber, lineIndex);
		lineTextContainer = new LineTextContainer(box, lineText, lineNumberContainer);
	}

	public void draw(Graphics2D g2d) {
		lineTextContainer.draw(g2d);
		lineNumberContainer.draw(g2d);
	}
	
	public LineNumberContainer getLineNumberContainer() {
		return lineNumberContainer;
	}
	
	public LineTextContainer getLineTextContainer() {
		return lineTextContainer;
	}
}