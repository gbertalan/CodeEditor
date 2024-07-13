import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CCompilerApp extends JFrame {
    private JTextField codeInputField;
    private JButton runButton;
    private JTextArea consoleOutputArea;

    public CCompilerApp() {
        setTitle("C Compiler App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        codeInputField = new JTextField();
        runButton = new JButton("Run");
        consoleOutputArea = new JTextArea();
        consoleOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleOutputArea);

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runCProgram(codeInputField.getText());
            }
        });

        setLayout(new BorderLayout());
        add(codeInputField, BorderLayout.NORTH);
        add(runButton, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void runCProgram(String code) {
        try {
            // Save the code to a temporary file
            File tempCFile = File.createTempFile("temp", ".c");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempCFile))) {
                writer.write(code);
            }

            // Compile the C file using gcc
            Process compileProcess = new ProcessBuilder("gcc", tempCFile.getAbsolutePath(), "-o", "tempExecutable")
                    .redirectErrorStream(true)
                    .start();
            int compileExitCode = compileProcess.waitFor();
            if (compileExitCode != 0) {
                String error = readStream(compileProcess.getInputStream());
                consoleOutputArea.setText("Compilation failed:\n" + error);
                return;
            }

            // Run the compiled executable
            Process runProcess = new ProcessBuilder("./tempExecutable")
                    .redirectErrorStream(true)
                    .start();
            String output = readStream(runProcess.getInputStream());
            consoleOutputArea.setText(output);

        } catch (IOException | InterruptedException ex) {
            consoleOutputArea.setText("An error occurred: " + ex.getMessage());
        }
    }

    private String readStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CCompilerApp().setVisible(true);
            }
        });
    }
}

