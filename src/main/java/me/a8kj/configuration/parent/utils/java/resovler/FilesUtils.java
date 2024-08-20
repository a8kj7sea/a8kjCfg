package me.a8kj.configuration.parent.utils.java.resovler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FilesUtils {

    /**
     * Copies a file from the source path to the destination path with a new
     * extension.
     * 
     * @param sourcePath     the path of the source file
     * @param destinationDir the directory where the file will be copied
     * @param newExtension   the new extension for the copied file
     * @throws IOException if an I/O error occurs
     */
    public static void copyFileWithNewExtension(Path sourcePath, Path destinationDir, String newExtension)
            throws IOException {
        // Check if the new extension starts with a dot
        if (!newExtension.startsWith(".")) {
            newExtension = "." + newExtension;
        }

        // Create the new file path with the new extension
        String fileName = sourcePath.getFileName().toString();
        String newFileName = fileName.replaceAll("\\.[^.]+$", "") + newExtension;
        Path destinationPath = destinationDir.resolve(newFileName);

        // Copy the file to the new location
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
