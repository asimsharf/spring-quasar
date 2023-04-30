package com.sudagoarth.springquasar.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtils {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IOException("Could Not Save file: " + fileName, ex);
        }
    }

    public static void cleanDir(String dir) throws IOException {
        try {
            Path dirPath = Paths.get(dir);
            Files.list(dirPath).forEach(file -> {
                if (!Files.isDirectory(file)) {
                    try {
                        Files.delete(file);
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Could not delete file" + e.getMessage());
                    }
                }
            });
        } catch (IOException e) {
            throw new IOException("Could not delete files", e);
        }
    }

    public static String renameFile(String fileName) {
        if (fileName.contains("..")) {
            throw new IllegalArgumentException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        String[] tokens = fileName.split("\\.");
        String extension = tokens[1];
        return System.currentTimeMillis() + "." + extension;
    }

    public static void removeDir(String dir) throws IOException {
        Path dirPath = Paths.get(dir);
        Files.list(dirPath).forEach(file -> {
            if (!Files.isDirectory(file)) {
                try {
                    Files.delete(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Files.delete(dirPath);
    }

    public static boolean isDirExists(String uploadDir) {
        Path uploadPath = Paths.get(uploadDir);
        return Files.exists(uploadPath);
    }

    public static void removeFile(String dir, String fileName) throws IOException {
        Path filePath = Paths.get(dir + "/" + fileName);
        Files.delete(filePath);
    }

}
