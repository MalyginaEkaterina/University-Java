import java.util.Arrays;

public class MyArrays {

    //Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
    // Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
    // идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
    // необходимо выбросить RuntimeException.
    public static int[] makeNewArr(int[] arr) {
        int end = 4;
        int ind = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == end) {
                ind = i;
            }
        }

        if (ind == -1) {
            throw new RuntimeException();
        } else {
            return Arrays.copyOfRange(arr, ind + 1, arr.length);
        }
    }

    //Написать метод, который проверяет состав массива из чисел 1 и 4.
    // Если в нем нет хоть одной четверки или единицы, то метод вернет false;
    public static boolean checkArr(int[] arr) {
        boolean contain1 = false;
        boolean contain4 = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                contain1 = true;
            } else if (arr[i] == 4) {
                contain4 = true;
            } else {
                return false;
            }
        }
        return contain1 && contain4;
    }
}
