package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};

        Random random = new Random();
        int numOfHiddenWord = random.nextInt(words.length);
        String hiddenWord = words[numOfHiddenWord];
        //System.out.println(hiddenWord);
        Scanner sc = new Scanner(System.in);
        boolean result;
        System.out.println("Угадайте слово");
        do {
            String userWord = sc.nextLine();
            result = hiddenWord.equals(userWord);
            if (userWord.length() == 0) {
                System.out.println("Введите слово");
                continue;
            }
            if (!result) {
                printMatchedChar(hiddenWord, userWord);
            }
        } while (!result);
        System.out.println("Вы угадали!");
        sc.close();
    }

    public static void printMatchedChar(String word1, String word2) {
        System.out.print("Попробуйте еще, угаданные буквы: ");
        int minWordLength = Math.min(word1.length(), word2.length());
        for (int i = 0; i < minWordLength; i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                System.out.print(word1.charAt(i));
            } else {
                System.out.print("#");
            }
        }
        for (int i = 0; i < 15 - minWordLength; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
}
