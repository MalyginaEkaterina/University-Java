package ru.geekbrains;


public class Main {

    public static void main(String[] args) {
        String s = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        try {
            String[][] arr = makeArrayFromString(s);
            printArr(arr);
            System.out.println("Calculation result: " + calcExpr2(arr));
        } catch (LengthOfArrayException | InvalidArrayContentException e) {
            e.printStackTrace();
        }
    }

    public static String[][] makeArrayFromString(String s) {
        String[] arr = s.split("\n");
        if (arr.length != 4) {
            throw new LengthOfArrayException("Размер матрицы не 4x4");
        }
        String[][] result = new String[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i].split(" ");
            if (result[i].length != 4) {
                throw new LengthOfArrayException("Размер матрицы не 4x4");
            }
        }
        return result;
    }

    //2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;
    public static int calcExpr2(String[][] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    res += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new InvalidArrayContentException(i, j);
                }
            }
        }
        return res / 2;
    }

    public static void printArr(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
