package ru.geekbrains;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся)
        String[] arrOfWords = ("Жили были дед да баба и была у них курочка Ряба Снесла курочка яичко да яичко не " +
                "простое а яичко золотое дед бил бил не разбил баба бил бил не разбила").split(" ");
        List<String> listOfWords = Arrays.asList(arrOfWords);
        System.out.println(listOfWords);
        //Найти список слов, из которых состоит текст (дубликаты не считать);
        Set<String> setOfWords = new HashSet<>(listOfWords);
        System.out.println(setOfWords);
        //Посчитать сколько раз встречается каждое слово (использовать HashMap);
        HashMap<String, Integer> countOfWords = new HashMap<>();
        for (String word : listOfWords) {
            countOfWords.put(word, countOfWords.getOrDefault(word, 0) + 1);
        }
        System.out.println(countOfWords);

        //2. Написать простой класс PhoneBook(внутри использовать HashMap)
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPerson("Иванов", "70000000001", "who1@ya.ru");
        phoneBook.addPerson("Васильев", "70000000002", "who2@ya.ru");
        phoneBook.addPerson("Иванов", "70000000003", "who3@ya.ru");
        phoneBook.addPerson("Журавлев", "70000000004", "who4@ya.ru");
        phoneBook.addPerson("Васильев", "70000000005", "who5@ya.ru");
        phoneBook.info();
        System.out.println(phoneBook.getPhones("Иванов"));
        System.out.println(phoneBook.getEmails("Васильев"));
    }
}
