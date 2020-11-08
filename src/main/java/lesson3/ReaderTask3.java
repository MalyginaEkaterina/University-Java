package lesson3;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

//Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
// Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
// Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
public class ReaderTask3 {
    final static int PAGE_SIZE = 1800;

    public static void main(String[] args) {
        File file = null;
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileopen.getSelectedFile();
        } else {
            file = new File("task3.txt");
        }

        try (RandomAccessFile r = new RandomAccessFile(file, "r")) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Введите номер страницы. Для выхода введите Exit");
                if (sc.hasNextInt()) {
                    int page = sc.nextInt();
                    showPageFromFile(r, page);
                    System.out.println();
                } else if (sc.hasNextLine()) {
                    String s = sc.next();
                    if (s.equalsIgnoreCase("exit")) {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showPageFromFile(RandomAccessFile r, int page) throws IOException {
        long pos = (page - 1) * PAGE_SIZE;
        if (pos < r.length()) {
            byte[] byteArr = new byte[PAGE_SIZE];
            r.seek(pos);
            int count = r.read(byteArr);
            System.out.println(new String(byteArr, 0, count, "UTF-8"));
        } else {
            long numPages = (r.length() + PAGE_SIZE - 1) / PAGE_SIZE;
            System.out.println("В файле отсутствует такая страница. Всего страниц: " + numPages);
        }
    }

}
