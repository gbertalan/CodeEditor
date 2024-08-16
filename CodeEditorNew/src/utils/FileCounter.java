package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileCounter {

    public static void main(String[] args) {
        // Specify the folder path
        String folderPath = "C:\\Users\\Garry Bertalan\\Desktop\\Git_Cloned_Repositories\\CodeEditor\\CodeEditorNew\\src";

        File rootFolder = new File(folderPath);
        if (!rootFolder.isDirectory()) {
            System.out.println("The given path is not a directory.");
            return;
        }

        // Start the recursive process
        int[] result = countFilesAndLines(rootFolder);

        System.out.println("Total number of files: " + result[0]);
        System.out.println("Total number of lines: " + result[1]);
    }

    private static int[] countFilesAndLines(File folder) {
        int fileCount = 0;
        int totalLines = 0;

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileCount++;
                    totalLines += countLines(file);
                } else if (file.isDirectory()) {
                    System.out.println("Entering subfolder: " + file.getPath());
                    int[] subfolderResult = countFilesAndLines(file);
                    System.out.println("Subfolder: " + file.getPath() + " has " + subfolderResult[0] + " files.");
                    fileCount += subfolderResult[0];
                    totalLines += subfolderResult[1];
                }
            }
        }

        return new int[]{fileCount, totalLines};
    }

    private static int countLines(File file) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getName());
        }
        return lines;
    }
}
