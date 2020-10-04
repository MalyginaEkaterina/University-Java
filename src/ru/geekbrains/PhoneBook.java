package ru.geekbrains;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, ArrayList<Person>> book = new HashMap<>();

    public PhoneBook() {
    }

    public void addPerson(String surname, String phone, String email) {
        Person person = new Person(surname, phone, email);
        ArrayList<Person> arr = book.getOrDefault(surname, new ArrayList<Person>());
        arr.add(person);
        book.put(surname, arr);
    }

    public void info() {
        System.out.println(book);
    }

    public ArrayList<String> getPhones(String surname) {
        ArrayList<String> phones = new ArrayList<>();
        if (book.containsKey(surname)) {
            for (Person person : book.get(surname)) {
                phones.add(person.getPhone());
            }
        }
        return phones;
    }

    public ArrayList<String> getEmails(String surname) {
        ArrayList<String> emails = new ArrayList<>();
        if (book.containsKey(surname)) {
            for (Person person : book.get(surname)) {
                emails.add(person.getEmail());
            }
        }
        return emails;
    }
}
