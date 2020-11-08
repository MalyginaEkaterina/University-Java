package lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        readFileAndPrint("task1.txt");
        joinFiles();
    }

    //Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
    public static void readFileAndPrint(String fileName) {
        byte[] byteArr = new byte[50];
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int count;
            while ((count = fis.read(byteArr)) > 0) {
                for (int i = 0; i < count; i++) {
                    System.out.print(byteArr[i] + " ");
                }
                System.out.println();
                System.out.println(new String(byteArr, 0, count, "UTF-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
    public static void joinFiles() {
        ArrayList<InputStream> streamArrayList = new ArrayList<>();
        SequenceInputStream in = null;

        try (FileOutputStream out = new FileOutputStream("task2-out.txt")){
            streamArrayList.add(new FileInputStream("task2-1.txt"));
            streamArrayList.add(new FileInputStream("task2-2.txt"));
            streamArrayList.add(new FileInputStream("task2-3.txt"));
            streamArrayList.add(new FileInputStream("task2-4.txt"));
            streamArrayList.add(new FileInputStream("task2-5.txt"));
            in = new SequenceInputStream(Collections.enumeration(streamArrayList));
            byte[] byteArr = new byte[100];
            int count;
            while ((count = in.read(byteArr)) > 0) {
                out.write(byteArr, 0, count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                } else {
                    for (InputStream str: streamArrayList) {
                        str.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
