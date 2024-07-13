import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicExecutorFrame extends JFrame {

    private DrawingPanel drawingPanel;

    public DynamicExecutorFrame() {
        initComponents();
    }

    private void initComponents() {
        drawingPanel = new DrawingPanel();

        JButton executeButton = new JButton("Execute");
        JTextField commandField = new JTextField(20);

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandField.getText().trim();
                if (!command.isEmpty()) {
                    try {
                        DynamicCodeExecutor.execute(drawingPanel.getGraphics2D(), command);
                        drawingPanel.repaint();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Enter command:"));
        controlPanel.add(commandField);
        controlPanel.add(executeButton);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
    }
}
