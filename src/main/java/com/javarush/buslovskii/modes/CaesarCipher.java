package com.javarush.buslovskii.modes;

public class CaesarCipher {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\"':-!?";

    public String process(String text, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        int alphabetSize = ALPHABET.length();

        for (char character : text.toCharArray()) {
            char lowerCaseChar = Character.toLowerCase(character);
            int index = ALPHABET.indexOf(lowerCaseChar);

            if (index != -1) {
                int newIndex;
                if (encrypt) {
                    newIndex = (index + key) % alphabetSize;
                } else {
                    newIndex = (index - key + alphabetSize) % alphabetSize;
                }

                char newChar = ALPHABET.charAt(newIndex);

                if (Character.isUpperCase(character)) {
                    result.append(Character.toUpperCase(newChar));
                } else {
                    result.append(newChar);
                }
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public int getAlphabetSize() {
        return ALPHABET.length();
    }
}
