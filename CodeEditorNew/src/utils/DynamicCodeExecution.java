package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class DynamicCodeExecution extends JFrame {
    private JTextArea codeTextArea;
    private JButton executeButton;
    private Graphics2D g2d;
    
    public String text = "HAJAJAJA";

    public DynamicCodeExecution() {
        super("Dynamic Code Execution Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Initialize components
        codeTextArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(codeTextArea);
        executeButton = new JButton("Execute");

        // Layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(executeButton, BorderLayout.SOUTH);
        add(panel);

        // Action listener for execute button
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeUserCode();
            }
        });

        setVisible(true);
    }

    // Method to execute user code
    private void executeUserCode() {
        String userCode = codeTextArea.getText();

        // Prepare source code file
        File parentDir = new File("C:\\Users\\Garry Bertalan\\Desktop\\Git_Cloned_Repositories\\CodeEditor\\CodeEditorNew\\");
        if (!parentDir.exists()) {
            boolean created = parentDir.mkdirs(); // Create directories if they don't exist
            if (!created) {
                throw new RuntimeException("Failed to create parent directories for source file: " + parentDir.getAbsolutePath());
            }
        }

        File sourceFile = new File(parentDir, "UserPaintCode.java");
        try {
            FileWriter writer = new FileWriter(sourceFile);
            writer.write("import java.awt.*;\n");
            writer.write("public class UserPaintCode {\n");
            writer.write("    public void usercode(Graphics2D g2d) {\n");
            writer.write(userCode + "\n");  // User-provided code
            writer.write("    }\n");
            writer.write("}\n");
            writer.close();

            // Compile the source file
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(sourceFile);
            compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
            fileManager.close();

            // Load and instantiate the class dynamically
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDir.toURI().toURL() });
            Class<?> userClass = Class.forName("UserPaintCode", true, classLoader);
            Object userInstance = userClass.getDeclaredConstructor().newInstance();

            // Execute usercode method
            Method usercodeMethod = userClass.getMethod("usercode", Graphics2D.class);

            // Create a new BufferedImage and get its Graphics2D context
            BufferedImage image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);
            g2d = image.createGraphics();

            // Execute user's code with the Graphics2D object
            usercodeMethod.invoke(userInstance, g2d);

            // Display the image (optional step to visualize the result)
            ImageIcon icon = new ImageIcon(image);
            JOptionPane.showMessageDialog(this, new JLabel(icon), "Result", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error executing user code: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Clean up: Delete the source file if needed
            if (sourceFile.exists()) {
                sourceFile.delete();
            }
            // Dispose of the Graphics2D object
            if (g2d != null) {
                g2d.dispose();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DynamicCodeExecution();
            }
        });
    }
}
