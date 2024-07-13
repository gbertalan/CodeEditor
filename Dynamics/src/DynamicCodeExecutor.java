import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class DynamicCodeExecutor {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            DynamicExecutorFrame frame = new DynamicExecutorFrame();
            frame.setTitle("Dynamic Code Executor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.setVisible(true);
        });
    }

    public static void execute(Graphics2D g2d, String userText) throws Exception {
        if (userText.startsWith("g2d.fillRect")) {
            executeFillRect(userText, g2d);
        }
        // Add more commands here as needed
    }

    private static void executeFillRect(String userText, Graphics2D g2d) throws Exception {
        // Extract parameters from userText
        String[] parts = userText.substring(userText.indexOf("(") + 1, userText.indexOf(")")).split(",");
        int param1 = Integer.parseInt(parts[0].trim());
        int param2 = Integer.parseInt(parts[1].trim());
        int param3 = Integer.parseInt(parts[2].trim());
        int param4 = Integer.parseInt(parts[3].trim());

        // Call g2d.fillRect(param1, param2, param3, param4) using reflection
        Method fillRectMethod = Graphics.class.getMethod("fillRect", int.class, int.class, int.class, int.class);
        fillRectMethod.invoke(g2d, param1, param2, param3, param4);
    }
}
