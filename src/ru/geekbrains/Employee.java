package ru.geekbrains;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private long salary;
    private int age;

    Employee(String fullName, String position, String email, String phone, long salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    void info() {
        System.out.println("ФИО: " + this.fullName + "; должность: " + position + "; email: " + email +
                "; телефон: " + phone + "; зарплата: " + salary + "; возраст: " + age);
    }

    int getAge() {
        return this.age;
    }
}
