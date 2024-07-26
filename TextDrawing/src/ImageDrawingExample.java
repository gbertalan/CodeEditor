import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImageDrawingExample extends JPanel {
    private BufferedImage textImage;

    public ImageDrawingExample() {
        // Create a BufferedImage to draw text
        int width = 200;  // Width of the image
        int height = 50;  // Height of the image
        textImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = textImage.createGraphics();
        
        setRenderingHints(g2d);
        
        try {
            // Set the font and draw the text
            Font font = new Font("Serif", Font.PLAIN, 20);
            g2d.setFont(font);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Hello, World!", 10, 30);
        } finally {
            g2d.dispose();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setRenderingHints(g2d);
        // Draw the previously created image onto the panel
        
        BufferedImage resizedImage = resize(textImage, 400, 100);
        g2d.drawImage(resizedImage, 50, 50, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ImageDrawingExample());
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
    
    public static void setRenderingHints(Graphics2D g2d) {
		/*
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON);
		 * g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
		 * RenderingHints.VALUE_RENDER_QUALITY);
		 * g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
		 * RenderingHints.VALUE_STROKE_PURE);
		 */
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}
