package com.javarush.buslovskii.modes;

public class BruteForce {
    private final CaesarCipher cipher = new CaesarCipher();

    public String breakCipher(String encryptedText) {
        int bestKey = 0;
        int maxScore = -1;

        for (int key = 1; key < cipher.getAlphabetSize(); key++) {
            String decryptedText = cipher.process(encryptedText, key, false);
            int currentScore = evaluateText(decryptedText);

            if (currentScore > maxScore) {
                maxScore = currentScore;
                bestKey = key;
            }
        }

        System.out.println("Brute Force: Наиболее вероятный ключ: " + bestKey);
        return cipher.process(encryptedText, bestKey, false);
    }

    private int evaluateText(String text) {
        int score = 0;

        if (text.matches(".*[.,]\\s[а-яА-ЯЁё].*")) {
            score += 10;
        }
        String[] commonWords = {" и ", " в ", " не ", " на ", " он ", " с ", " или "};
        for (String word : commonWords) {
            if (text.contains(word)) {
                score++;
            }
        }
        return score;
    }
}
