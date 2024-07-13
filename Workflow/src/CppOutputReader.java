
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CppOutputReader {
    public static void main(String[] args) {
        String executableName = "./HelloWorld";

        try {
            // Run the compiled executable
            Process runProcess = new ProcessBuilder(executableName).start();
            try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
                String line;
                while ((line = outputReader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Wait for the process to complete
            runProcess.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
