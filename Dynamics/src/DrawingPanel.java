import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    private Graphics2D g2d;

    public DrawingPanel() {
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);
        // Additional setup for anti-aliasing, etc., can be done here
        // Example: g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public Graphics2D getGraphics2D() {
        return g2d;
    }
}
