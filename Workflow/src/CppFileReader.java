
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CppFileReader {
    public static void main(String[] args) {
        String cppFileName = "HelloWorld.cpp";

        try (BufferedReader reader = new BufferedReader(new FileReader(cppFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

