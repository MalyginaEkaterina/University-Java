package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        byte val1 = -10;
        short val2 = 1000;
        int val3 = 120;
        long val4 = 120000000L;
        float val5 = 120.1f;
        double val6 = 1234.1;
        char val7 = '#';
        boolean val8 = true;

        System.out.println("a*(b+(c/d)) = " + calc(4, 5, 8, 2));
        System.out.println("a+b between 10 and 20 = " + compareSum(3, 3));
        isPositive(-10);
        System.out.println(isNegative(-1));
        printName("Екатерина");
        isLeapYear(400);
        isLeapYear2(400);
    }

    public static int calc(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    public static boolean compareSum(int a, int b) {
        return ((a + b) >= 10) && ((a + b) <= 20);
    }

    public static void isPositive(int a) {
        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean isNegative(int a) {
        return (a < 0);
    }

    public static void printName(String name) {
        System.out.println("Привет, " + name + "!");
    }

    public static void isLeapYear(int year) {
        boolean result = false;
        if (year % 4 == 0) {
            if (year % 400 == 0) {
                result = true;
            } else if (year % 100 != 0) {
                result = true;
            }
        }
        if (result) {
            System.out.println("Год " + year + " високосный");
        } else {
            System.out.println("Год " + year + " не високосный");
        }
    }

    public static void isLeapYear2(int year) {
        boolean result = (year % 4 == 0) && ((year % 400 == 0) || (year % 100 != 0));
        if (result) {
            System.out.println("Год " + year + " високосный");
        } else {
            System.out.println("Год " + year + " не високосный");
        }
    }
}
