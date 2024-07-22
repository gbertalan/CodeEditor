package view.window.mainUI.component.box;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.StringTokenizer;

import utils.Theme;

public class BoxContent implements BoxComponent {

    private Box box;
    private ArrayList<String> contentLineList;

    public BoxContent(Box box, ArrayList<String> contentLineList) {
        this.box = box;
        this.contentLineList = contentLineList;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Theme.getBoxBackgroundColor());
        int locX = box.getLocX();
        int locY = box.getLocY() + box.getBoxHeader().getHeight();
        int width = box.getWidth();
        int height = box.getHeight() - box.getBoxHeader().getHeight();
        g2d.fillRect(locX, locY, width, height);

        ContentLine contentLine = new ContentLine(box);
        int currentIndex = 0;

        for (String line : contentLineList) {
            ArrayList<String> brokenLines = breakLine(line, g2d);
            for (String brokenLine : brokenLines) {
                contentLine.setLineIndex(currentIndex++);
                contentLine.setlineText(brokenLine);
                contentLine.draw(g2d);
            }
        }
    }

    private ArrayList<String> breakLine(String line, Graphics2D g2d) {
        ArrayList<String> brokenLines = new ArrayList<>();
        int width = box.getWidth();
        int leftMargin = width / ContentLine.TEXT_LEFT_MARGIN_DIVISOR;
        int maxTextWidth = width - 2 * leftMargin;

        // Tokenize the line into words
        StringTokenizer tokenizer = new StringTokenizer(line);
        StringBuilder currentLine = new StringBuilder();
        FontMetrics metrics = g2d.getFontMetrics();

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            String testLine = currentLine.length() > 0 ? currentLine + " " + word : word;

            // If the test line width exceeds the maximum width, break the line
            if (metrics.stringWidth(testLine) > maxTextWidth) {
                if (currentLine.length() > 0) {
                    brokenLines.add(currentLine.toString());
                    currentLine = new StringBuilder(word);
                } else {
                    // If a single word is too long, add it anyway and proceed
                    brokenLines.add(word);
                }
            } else {
                currentLine.append(currentLine.length() > 0 ? " " : "").append(word);
            }
        }
        if (currentLine.length() > 0) {
            brokenLines.add(currentLine.toString());
        }

        return brokenLines;
    }
}
