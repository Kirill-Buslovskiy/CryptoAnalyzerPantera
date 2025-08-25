package com.javarush.buslovskii.files;

import com.javarush.buslovskii.exceptions.FileExceptions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    public String read(String filePath) {
        if (!Files.exists(Paths.get(filePath))) {
            throw new FileExceptions("Исходный файл не найден по пути: " + filePath, null);
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new FileExceptions("Ошибка при чтении файла.", e);
        }
        return content.toString();
    }

    public void write(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new FileExceptions("Ошибка при записи в файл.", e);
        }
    }
}
