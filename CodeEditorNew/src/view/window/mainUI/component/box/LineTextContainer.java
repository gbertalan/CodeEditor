package view.window.mainUI.component.box;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import utils.SyntaxColor;

public class LineTextContainer {

    private static final double HEIGHT_DIVISOR = 36;

    private Box box;
    private LineNumberContainer lineNumberContainer;
    private String lineText;
    private String subText;
    private int locX;
    private int locY;
    private int width;
    private int height;

    private Font font;
    private FontMetrics metrics;

    public LineTextContainer(Box box, String lineText, LineNumberContainer lineNumberContainer) {
        this.box = box;
        this.lineText = lineText.replace("\t", "\u00A0\u00A0\u00A0\u00A0");
        this.lineNumberContainer = lineNumberContainer;

        updateLocationAndSize();
        this.font = new Font("Consolas", Font.BOLD, (int) Math.round(box.getHeight() / HEIGHT_DIVISOR));
    }

    private void updateLocationAndSize() {
        locX = lineNumberContainer.getLocX() + lineNumberContainer.getWidth();
        locY = lineNumberContainer.getLocY();
        width = box.getWidth() - lineNumberContainer.getWidth();
        height = box.getHeight();
    }

    public void draw(Graphics2D g2d) {
        updateLocationAndSize();
        g2d.setColor(SyntaxColor.getDefaultTextColor());
        g2d.setFont(font);

        if (metrics == null) {
            metrics = g2d.getFontMetrics(font);
        }

        StringBuilder subTextBuilder = new StringBuilder();
        int textWidth = 0;
        for (char c : lineText.toCharArray()) {
            int charWidth = metrics.charWidth(c);
            if (textWidth + charWidth > width) {
                break;
            }
            subTextBuilder.append(c);
            textWidth += charWidth;
        }
        subText = subTextBuilder.toString();

        int textHeight = locY + metrics.getAscent();
        int drawX = locX;
        for (char c : subText.toCharArray()) {
            g2d.drawString(String.valueOf(c), drawX, textHeight);
            drawX += metrics.charWidth(c);
        }
    }
}
