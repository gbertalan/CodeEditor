package visual_components;
import java.awt.Graphics2D;

public interface VisualComponent {

    void draw(Graphics2D g2d);

    int getLocX();

    int getLocY();

    int getWidth();

    int getHeight();
}
