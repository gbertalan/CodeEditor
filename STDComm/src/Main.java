
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        try {
            // Start Program A (Producer)
            ProcessBuilder producerBuilder = new ProcessBuilder("java", "Producer");
            Process producerProcess = producerBuilder.start();

            // Start Program B (Consumer)
            ProcessBuilder consumerBuilder = new ProcessBuilder("java", "Consumer");
            Process consumerProcess = consumerBuilder.start();

            // Get the output stream of Producer and input stream of Consumer
            try (InputStream producerOutput = producerProcess.getInputStream();
                 OutputStream consumerInput = consumerProcess.getOutputStream()) {

                // Pipe Producer's output to Consumer's input
                byte[] buffer = new byte[1024];
                int length;
                while ((length = producerOutput.read(buffer)) != -1) {
                    consumerInput.write(buffer, 0, length);
                }
            }

            // Wait for processes to complete
            producerProcess.waitFor();
            consumerProcess.waitFor();

            // Print Consumer's output
            try (InputStream consumerOutput = consumerProcess.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(consumerOutput))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
