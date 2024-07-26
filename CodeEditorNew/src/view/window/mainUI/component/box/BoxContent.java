package view.window.mainUI.component.box;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import utils.Globals;
import utils.Theme;

public class BoxContent implements BoxComponent {

    private Box box;

    private int locX;
    private int locY;
    private int width;
    private int height;

    private ArrayList<DisplayedLine> displayedLines;
    private BufferedImage contentImage;

    public BoxContent(Box box, ArrayList<String> fileLineList, int startFileLineIndex) {
        this.box = box;
        this.displayedLines = new ArrayList<>();

        // Initialize location and size
        updateLocationAndSize();

        // Create displayed lines, unbroken:
        int lineIndex = 0;
        for (String fileLine : fileLineList) {
            displayedLines.add(new DisplayedLine(box, startFileLineIndex + lineIndex + 1, fileLine, lineIndex));
            ++lineIndex;
        }

        // Create image
        createImage();
    }

    private void updateLocationAndSize() {
        locX = box.getLocX();
        locY = box.getLocY() + box.getBoxHeader().getHeight();
        width = box.getWidth();
        height = box.getHeight() - box.getBoxHeader().getHeight();
    }

    private void createImage() {
        updateLocationAndSize();

        contentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = contentImage.createGraphics();

        Globals.setRenderingHints(g2d);

        // Draw displayed lines
        for (DisplayedLine displayedLine : displayedLines) {
            displayedLine.draw(g2d);
        }

        g2d.dispose();
    }

    @Override
    public void draw(Graphics2D g2d) {
        updateLocationAndSize();

        // Draw background
        g2d.setColor(Theme.getBoxBackgroundColor());
        g2d.fillRect(locX, locY, width, height);

        BufferedImage imageToDraw = contentImage;
        if (contentImage.getWidth() != width || contentImage.getHeight() != height) {
            imageToDraw = Globals.resize(contentImage, width, height);
        }

        g2d.drawImage(imageToDraw, locX, locY, null);
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
}
