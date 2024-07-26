package view.window.mainUI.component.box;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import utils.ANSIText;
import utils.SyntaxColor;
import utils.Theme;

public class LineTextContainer {

    private static double TOP_MARGIN_DIVISOR = 16;
    private static double HEIGHT_DIVISOR = 36;
    static double TEXT_LEFT_MARGIN_DIVISOR = 20;
    private Box box;
    private LineNumberContainer lineNumberContainer;
    private String lineText;
    static public int limitLineLocationX;

    private String subText;
    private int locX;
    private int locY;
    private int width;
    private int height;

    private Font font;
    private int fontSize;
    private FontMetrics metrics;

    public LineTextContainer(Box box, String lineText, LineNumberContainer lineNumberContainer) {
        System.out.println(ANSIText.red("LineTextContainer constructor is called"));
        this.box = box;
        this.lineText = lineText.replace("\t", "\u00A0\u00A0\u00A0\u00A0");
        this.lineNumberContainer = lineNumberContainer;

        updateLocationAndSize();

        // Set the font size based on initial height
        this.fontSize = (int) Math.round(box.getHeight() / HEIGHT_DIVISOR);
        this.font = new Font("Consolas", Font.BOLD, fontSize);

        // Initialize subText here since we need metrics in draw() to be accurate
        this.subText = "";
    }

    private void updateLocationAndSize() {
        locX = lineNumberContainer.getLocX() + lineNumberContainer.getWidth();
        locY = lineNumberContainer.getLocY();
        width = box.getWidth() - lineNumberContainer.getWidth();
        height = box.getHeight();
    }

    public void draw(Graphics2D g2d) {
//        g2d.setColor(Theme.getBoxBackgroundColor());
        updateLocationAndSize();
//        g2d.fillRect(locX, locY, width, height);

        g2d.setColor(SyntaxColor.getDefaultTextColor());
        g2d.setFont(font);

        // Ensure metrics is initialized correctly with the current graphics context
        metrics = g2d.getFontMetrics(font);

        subText = "";
        int textWidth = 0;
        for (char c : lineText.toCharArray()) {
            int charWidth = metrics.charWidth(c);
            if (textWidth + charWidth > width) {
                break;
            }
            subText += c;
            textWidth += charWidth;
        }

        int textHeight = locY + metrics.getAscent();
        int drawX = locX;
        for (char c : subText.toCharArray()) {
            g2d.drawString(String.valueOf(c), drawX, textHeight);
            drawX += metrics.charWidth(c);
        }
    }

}
