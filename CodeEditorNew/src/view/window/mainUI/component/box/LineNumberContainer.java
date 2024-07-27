package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Graphics2D;

import utils.Globals;
import utils.SyntaxColor;

public class LineNumberContainer implements BoxComponent {

    private static final double HEIGHT_DIVISOR = 36;

    private Box box;
    private int lineNumber;
    private int lineIndex;
    private int locX;
    private int locY;
    private int width;
    private int height;

    public LineNumberContainer(Box box, Integer lineNumber, int lineIndex) {
        this.box = box;
        this.lineNumber = lineNumber != null ? lineNumber : 0;
        this.lineIndex = lineIndex;
        updateLocationAndSize();
    }

    private void updateLocationAndSize() {
        locX = 0;
        locY = (int) Math.round((box.getHeight() / HEIGHT_DIVISOR) * lineIndex);
        width = box.getWidth() / 8;
        height = (int) Math.round(box.getHeight() / HEIGHT_DIVISOR);
    }

    @Override
    public void draw(Graphics2D g2d) {
    	g2d.setColor(Color.BLACK);
    	g2d.fillRect(locX, locY, width, height);
        g2d.setColor(SyntaxColor.getLineNumberColor());
        Globals.drawCenteredText(g2d, locX, locY, width, height, Integer.toString(lineNumber));
    }

    public void setLineNumber(int number) {
        this.lineNumber = number;
    }

    public void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getLineIndex() {
        return lineIndex;
    }
}
