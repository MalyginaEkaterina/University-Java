package ru.geekbrains;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //1. Задать целочисленный массив, состоящий из элементов 0 и 1.
        // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;

        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = 1 - arr1[i];
        }
        System.out.println(Arrays.toString(arr1));

        //2. Задать пустой целочисленный массив размером 8.
        // С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;

        int[] arr2 = new int[8];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i * 3;
        }
        System.out.println(Arrays.toString(arr2));

        //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        // пройти по нему циклом, и числа меньшие 6 умножить на 2;

        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = arr3[i] < 6 ? arr3[i] * 2 : arr3[i];
        }
        System.out.println(Arrays.toString(arr3));

        //4. Создать квадратный двумерный целочисленный массив
        // (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
        // заполнить его диагональные элементы единицами;

        int[][] arr4 = new int[5][5];
        for (int i = 0; i < arr4.length; i++) {
            arr4[i][i] = 1;
            arr4[i][arr4.length - 1 - i] = 1;
        }
        printArr(arr4);

        //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы

        int[] arr5 = {-3, -5, 3, 0, 11, 4, 5, 2};
        if (arr5.length > 0) {
            int min = arr5[0];
            int max = arr5[0];
            for (int i = 1; i < arr5.length; i++) {
                min = arr5[i] < min ? arr5[i] : min;
                max = arr5[i] > max ? arr5[i] : max;
            }
            System.out.println("min = " + min + "; max = " + max);
        }

        //6.
        int[] arr6 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println("Массив " + Arrays.toString(arr6) + " имеет середину по сумме: " + isBalancedArray(arr6));

        //7.
        int[] arr7 = {1,2,3,4,5,6,7,8,9};
        shiftArray(arr7, 3);

        int[] arr7_2 = {9};
        shiftArray(arr7_2, 1);

        int[] arr7_3 = {1,2,3,4};
        shiftArray(arr7_3, 2);

        int[] arr7_4 = {1,2,3,4};
        shiftArray(arr7_4, 3);

        int[] arr7_5 = {1,2,3,4,5};
        shiftArray(arr7_5, 3);
    }

    public static boolean isBalancedArray(int[] arr) {
        //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
        // метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой
        // части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
        // checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
        if (arr.length < 2) {
            return false;
        }

        int fullSum = 0;

        for (int i = 0; i < arr.length; i++) {
            fullSum += arr[i];
        }

        if (fullSum % 2 != 0) {
            //сумма всех элементов не кратна двум
            return false;
        } else {
            int halfSum = fullSum / 2;
            int leftSum = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                leftSum += arr[i];
                if (leftSum == halfSum) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void shiftArray(int[] arr, int n) {
        //7. **** Написать метод, которому на вход подается одномерный массив и число n
        // (может быть положительным, или отрицательным), при этом метод должен сместить
        // все элементы массива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        if (arr.length == 0) {
            System.out.println("Передан пустой массив");
            return;
        }

        System.out.println("исходный массив: " + Arrays.toString(arr));
        int step = n % arr.length;

        if (step == 0) {
            System.out.println("массив не был изменен: " + Arrays.toString(arr));
            return;
        }

        int i = 0;
        int currInd = 0;
        int startInd = 0;
        int currValue = arr[0];

        while (i < arr.length) {
            int nextInd = (step >= 0) ? ((currInd + step) % arr.length) : ((currInd + arr.length + step) % arr.length);
            if (nextInd != startInd) {
                int nextValue = arr[nextInd];
                arr[nextInd] = currValue;
                currInd = nextInd;
                currValue = nextValue;
            } else {
                arr[nextInd] = currValue;
                startInd++;
                currInd = startInd;
                currValue = arr[currInd];
            }
            i++;
        }
        System.out.println("измененный массив, сдвиг на " + n + ": " + Arrays.toString(arr));

    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
