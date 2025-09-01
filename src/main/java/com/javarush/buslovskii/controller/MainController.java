package com.javarush.buslovskii.controller;

import com.javarush.buslovskii.exceptions.FileExceptions;
import com.javarush.buslovskii.exceptions.KeyExceptions;
import com.javarush.buslovskii.files.FileManager;
import com.javarush.buslovskii.modes.BruteForce;
import com.javarush.buslovskii.modes.CaesarCipher;

import java.util.Scanner;



public class MainController {
    private final Scanner scanner = new Scanner(System.in);
    private final FileManager fileManager = new FileManager();
    private final CaesarCipher cipher = new CaesarCipher();
    private final BruteForce bruteForce = new BruteForce();

    private static final String SOURCE_FILE = "C:/Users/ms/IdeaProjects/CryptoAnalyzerPantera/text/text.txt";
    private static final String ENCRYPTED_FILE = "C:/Users/ms/IdeaProjects/CryptoAnalyzerPantera/text/encrypted.txt";
    private static final String DECRYPTED_FILE = "C:/Users/ms/IdeaProjects/CryptoAnalyzerPantera/text/decrypted.txt";
    private static final String BRUTEFORCE_FILE = "C:/Users/ms/IdeaProjects/CryptoAnalyzerPantera/text/bruteforce.txt";


    public void start() {
        while (true) {
            printMenu();
            String mode = scanner.nextLine();

            try {
                switch (mode) {
                    case "1":
                        encryptedMode();
                        break;
                    case "2":
                        decryptedMode();
                        break;
                    case "3":
                        bruteForceMode();
                        break;
                    case "0":
                        System.out.println("Выход из программы.");
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (FileExceptions e) {
                System.err.println("Ошибка файла: " + e.getMessage());
            } catch (KeyExceptions e) {
                System.err.println("Ошибка ключа: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("----------------------------------------");
        }
    }

    private void printMenu() {
        System.out.println("\nВыберите режим работы:");
        System.out.println("1. Шифрование");
        System.out.println("2. Расшифровка по ключу");
        System.out.println("3. Взлом шифра методом Brute Force");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор: ");
    }

    private int askForKey() {
        System.out.print("Введите ключ (целое число): ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new KeyExceptions("Ключ должен быть целым числом.", e);
        }
    }

    private void encryptedMode() {
        System.out.println("--- РЕЖИМ ШИФРОВАНИЯ ---");
        int key = askForKey();

        String content = fileManager.read(SOURCE_FILE);
        String encryptedContent = cipher.process(content, key, true);
        fileManager.write(ENCRYPTED_FILE, encryptedContent);

        System.out.println("Файл зашифрован. Результат сохранен в " + ENCRYPTED_FILE);
    }

    private void decryptedMode() {
        System.out.println("--- РЕЖИМ РАСШИФРОВКИ ---");
        int key = askForKey();

        String content = fileManager.read(ENCRYPTED_FILE);
        String decryptedContent = cipher.process(content, key, false);
        fileManager.write(DECRYPTED_FILE, decryptedContent);

        System.out.println("Файл расшифрован. Результат сохранен в " + DECRYPTED_FILE);
    }

    private void bruteForceMode() {
        System.out.println("--- РЕЖИМ BRUTEFORCE ---");

        String content = fileManager.read(ENCRYPTED_FILE);
        String decryptedContent = bruteForce.breakCipher(content);
        fileManager.write(BRUTEFORCE_FILE, decryptedContent);

        System.out.println("Взлом методом BruteForce завершен. Результат сохранен в " + BRUTEFORCE_FILE);
    }
}
