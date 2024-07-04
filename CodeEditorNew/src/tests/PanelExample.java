package tests;

import javax.swing.*;
import java.awt.*;

public class PanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            OuterPanel outerPanel = new OuterPanel();
            outerPanel.setLayout(null); // using null layout for custom positioning

            InnerPanel innerPanel = new InnerPanel();
            innerPanel.setBounds(50, 50, 200, 200); // setting the position and size of the inner panel

            outerPanel.add(innerPanel);

            frame.add(outerPanel);
            frame.setVisible(true);

            // Periodically repaint the outer panel to simulate frequent updates
            Timer timer = new Timer(1000, e -> outerPanel.repaint());
            timer.start();
        });
    }
}

class OuterPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // This ensures that the child components are painted
        // Custom painting for the outer panel
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight()); // Example: fill the panel with red
    }
}

class InnerPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensure the panel is properly rendered
        // Custom painting for the inner panel
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight()); // Example: fill the panel with blue
    }
}

