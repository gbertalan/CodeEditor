package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ReadWrite {

    private static File localFile = new File(".");

    /**
     * Reads the contents of a file as a single string.
     * 
     * @param path     The path to the directory containing the file.
     * @param fileName The name of the file to read.
     * @return The contents of the file as a string, or "File read error" if an error occurs.
     */
    public static String readFile(String path, String fileName) {
        StringBuilder stringFromFile = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path + "\\" + fileName))) {
            String newLine;
            while ((newLine = in.readLine()) != null) {
                stringFromFile.append(newLine).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e);
            return "File read error";
        }
        return stringFromFile.toString();
    }

    /**
     * Reads the contents of a file in the resources directory as a single string.
     * 
     * @param fileName The name of the file to read.
     * @return The contents of the file as a string, or an empty string if an error occurs.
     */
    public static String readFileInResources(String fileName) {
        try (BufferedReader in = new BufferedReader(new FileReader(localFile.getCanonicalPath() + "\\resources\\" + fileName))) {
            return in.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Reads the contents of a file in the resources directory as an ArrayList of strings.
     * 
     * @param fileName The name of the file to read.
     * @return An ArrayList containing each line of the file as a string.
     */
    public static ArrayList<String> readFileInResourcesAsArrayList(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(localFile.getCanonicalPath() + "\\resources\\" + fileName))) {
            String line;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Reads the contents of a file in a specified path as a single string.
     * 
     * @param fileName The name of the file to read.
     * @param path     The path to the directory containing the file.
     * @return The contents of the file as a string, or an empty string if an error occurs.
     */
    public static String readFileInPath(String fileName, String path) {
        try (BufferedReader in = new BufferedReader(new FileReader(path + "\\" + fileName))) {
            return in.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
         * Reads the contents of a file in a specified path line by line into an ArrayList.
         * 
         * @param pathWithName     The path to the directory containing the file, together with the fileName.
         * @return An ArrayList containing each line of the file, or an empty ArrayList if an error occurs.
         */
    public static ArrayList<String> readFileInPathAsArrayList(String pathWithName) {
        ArrayList<String> lines = new ArrayList<>();
        String fullPath = pathWithName;

        // Debugging output to verify the full path
        System.out.println("Attempting to read file at: " + fullPath);

        File file = new File(fullPath);
        if (!file.exists()) {
            System.out.println("File does not exist at path: " + fullPath);
            return lines; // Return empty list if the file does not exist
        }

        try (BufferedReader in = new BufferedReader(new FileReader(fullPath))) {
            String line;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }


    /**
     * Writes a string to a file in the resources directory.
     * 
     * @param fileName      The name of the file to write.
     * @param stringToWrite The string to write to the file.
     */
    public static void writeFileInResources(String fileName, String stringToWrite) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(localFile.getCanonicalPath() + "\\resources\\" + fileName))) {
            out.write(stringToWrite);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Writes a string to a file in a specified path.
     * 
     * @param fileName      The name of the file to write.
     * @param stringToWrite The string to write to the file.
     * @param path          The path to the directory containing the file.
     */
    public static void writeFileInPath(String fileName, String stringToWrite, String path) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path + "\\" + fileName))) {
            out.write(stringToWrite);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Reads an image from the resources directory and resizes it.
     * 
     * @param fileName The name of the image file to read.
     * @param width    The width to resize the image to.
     * @param height   The height to resize the image to.
     * @return The resized BufferedImage.
     */
    public static BufferedImage readImage(String fileName, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(localFile.getCanonicalPath() + "\\resources\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resize(image, width, height);
    }

    /**
     * Resizes a BufferedImage to the specified width and height.
     * 
     * @param img   The BufferedImage to resize.
     * @param newW  The width to resize the image to.
     * @param newH  The height to resize the image to.
     * @return The resized BufferedImage.
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
