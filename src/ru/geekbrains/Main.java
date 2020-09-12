package ru.geekbrains;

public class Main {

    public static void main(String[] args) {

        Employee[] empArray = new Employee[5];
        empArray[0] = new Employee("Иванов Иван Иванович", "инженер", "who0@ya.ru",
                "79152320000", 35000, 31);
        empArray[1] = new Employee("Николаев Илья Иванович", "главный инженер", "who1@ya.ru",
                "79152321111", 55000, 50);
        empArray[2] = new Employee("Ильин Иван Ильич", "младший инженер", "who2@ya.ru",
                "79152322222", 30000, 39);
        empArray[3] = new Employee("Антонов Антон Антонович", "механик", "who3@ya.ru",
                "79152323333", 31000, 40);
        empArray[4] = new Employee("Иванов Антон Николаевич", "главный механик", "who4@ya.ru",
                "79152324444", 36000, 45);

        for (int i = 0; i < empArray.length; i++) {
            if (empArray[i].getAge() >= 40) {
                empArray[i].info();
            }
        }
    }
}
