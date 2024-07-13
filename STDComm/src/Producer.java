
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) {
        String message = "Hello from Program A";
        try (OutputStream outputStream = System.out) {
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
