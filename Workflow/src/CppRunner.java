import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CppRunner {
    public static void main(String[] args) {
        String cppFileName = "HelloWorld.cpp";
        String executableName = "HelloWorld";

        try {
            // Compile the C++ program
            Process compileProcess = new ProcessBuilder("g++", cppFileName, "-o", executableName).start();
            compileProcess.waitFor();

            if (compileProcess.exitValue() != 0) {
                System.out.println("Compilation failed.");
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()))) {
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                return;
            }

            System.out.println("Compilation successful.");

            // Run the compiled executable
            Process runProcess = new ProcessBuilder("./" + executableName).start();
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
