package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char[][] field;
    public static final int SIZE = 5;
    public static final int SYMB_TO_WIN = 4;
    public static final char EMPTY_CHAR = '•';
    public static final char USER_CHAR = 'X';
    public static final char COMP_CHAR = 'O';
    public static int countOfMoves; //для определения ничьи
    public static final boolean LEVEL_UP = true; //для выключения более сложной игры компьютера (не завершено)

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initField();
        printField();
        while (true) {
            int[] userXY = userMove();
            printField();
            if (checkWin(userXY[0], userXY[1], USER_CHAR)) {
                System.out.println("Вы выиграли");
                break;
            }
            if (countOfMoves == SIZE * SIZE) {
                System.out.println("Ничья");
                break;
            }
            int[] compXY = compMove(userXY[0], userXY[1]);
            printField();
            if (checkWin(compXY[0], compXY[1], COMP_CHAR)) {
                System.out.println("Вы проиграли");
                break;
            }
            if (countOfMoves == SIZE * SIZE) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static boolean checkWin(int i, int j, char symb) {
        //проверим i-ю строку поля
        if (checkWinLine(i, j, 1, 0, symb)) {
            return true;
        }
        //проверим j-й столбец поля
        if (checkWinLine(i, j, 0, 1, symb)) {
            return true;
        }
        //проверим прямую диагональ
        if (checkWinLeftDiag(i, j, symb)) {
            return true;
        }
        //проверим обратную диагональ
        if (checkWinRightDiag(i, j, symb)) {
            return true;
        }
        return false;
    }

    public static boolean checkWinLeftDiag(int i, int j, char symb) {
        int k = i - Math.min(i, j);
        int n = j - Math.min(i, j);
        int countOfMatches = 0;
        while (k < SIZE && n < SIZE) {
            countOfMatches = (field[k][n] == symb) ? (countOfMatches + 1) : 0;
            if (countOfMatches == SYMB_TO_WIN) {
                return true;
            }
            k++;
            n++;
        }
        return false;
    }

    public static boolean checkWinRightDiag(int i, int j, char symb) {
        int k = i + Math.min(SIZE - 1 - i, j);
        int n = j - Math.min(SIZE - 1 - i, j);
        int countOfMatches = 0;
        while (k >= 0 && n < SIZE) {
            countOfMatches = (field[k][n] == symb) ? (countOfMatches + 1) : 0;
            if (countOfMatches == SYMB_TO_WIN) {
                return true;
            }
            k--;
            n++;
        }
        return false;
    }

    public static boolean checkWinLine(int i, int j, int vx, int vy, char symb) {
        int countOfMatches = 0;
        for (int k = 0; k < SIZE; k++) {
            //при проверке i-й строки будет field[i][k], при проверке j-го столбца будет field[k][j]
            countOfMatches = (field[i * vx + k * vy][k * vx + j * vy] == symb) ? (countOfMatches + 1) : 0;
            if (countOfMatches == SYMB_TO_WIN) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPrevWinLine(int[] compXY, int i, boolean isUser) {
        int y = -1;
        int countOfX = 0;
        int isDanger = 0;
        char ourChar, otherChar;
        if (isUser) {
            ourChar = USER_CHAR;
            otherChar = COMP_CHAR;
        } else {
            ourChar = COMP_CHAR;
            otherChar = USER_CHAR;
        }
        for (int k = 0; k < SIZE; k++) {
            if (field[i][k] == ourChar) {
                countOfX++;
            } else if (field[i][k] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && SIZE >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == SYMB_TO_WIN - 2) {
                        compXY[0] = i;
                        compXY[1] = (rand.nextInt(2) == 0) ? y : k;
                        return true;
                    }
                }
                y = k;
                isDanger = 1;
            } else if (field[i][k] == otherChar) {
                countOfX = 0;
                isDanger = 0;
            }
            if (countOfX == SYMB_TO_WIN - 1 && y >= 0) {
                compXY[0] = i;
                compXY[1] = y;
                return true;
            }
        }
        return false;
    }

    public static boolean checkPrevWinCol(int[] compXY, int j, boolean isUser) {
        int x = -1;
        int countOfX = 0;
        int isDanger = 0;
        char ourChar, otherChar;
        if (isUser) {
            ourChar = USER_CHAR;
            otherChar = COMP_CHAR;
        } else {
            ourChar = COMP_CHAR;
            otherChar = USER_CHAR;
        }
        for (int k = 0; k < SIZE; k++) {
            if (field[k][j] == ourChar) {
                countOfX++;
            } else if (field[k][j] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && SIZE >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == SYMB_TO_WIN - 2) {
                        compXY[0] = (rand.nextInt(2) == 0) ? x : k;
                        compXY[1] = j;
                        return true;
                    }
                }
                x = k;
                isDanger = 1;
            } else if (field[k][j] == otherChar) {
                countOfX = 0;
                isDanger = 0;
            }
            if (countOfX == SYMB_TO_WIN - 1 && x >= 0) {
                compXY[0] = x;
                compXY[1] = j;
                return true;
            }
        }
        return false;
    }

    public static boolean checkPrevWinLeftDiag(int[] compXY, int i, int j, boolean isUser) {
        int x = -1;
        int y = -1;
        int countOfX = 0;
        int isDanger = 0;
        char ourChar, otherChar;
        if (isUser) {
            ourChar = USER_CHAR;
            otherChar = COMP_CHAR;
        } else {
            ourChar = COMP_CHAR;
            otherChar = USER_CHAR;
        }

        int k = i - Math.min(i, j);
        int n = j - Math.min(i, j);
        while (k < SIZE && n < SIZE) {
            if (field[k][n] == ourChar) {
                countOfX++;
            } else if (field[k][n] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && SIZE >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == SYMB_TO_WIN - 2) {
                        if (rand.nextInt(2) == 0) {
                            compXY[0] = x;
                            compXY[1] = y;
                        } else {
                            compXY[0] = k;
                            compXY[1] = n;
                        }
                        return true;
                    }
                }
                x = k;
                y = n;
                isDanger = 1;
            } else if (field[k][n] == otherChar) {
                countOfX = 0;
                isDanger = 0;
            }
            if (countOfX == SYMB_TO_WIN - 1 && x >= 0) {
                compXY[0] = x;
                compXY[1] = y;
                return true;
            }
            k++;
            n++;
        }

        return false;
    }

    public static boolean checkPrevWinRightDiag(int[] compXY, int i, int j, boolean isUser) {
        int x = -1;
        int y = -1;
        int countOfX = 0;
        int isDanger = 0;
        char ourChar, otherChar;
        if (isUser) {
            ourChar = USER_CHAR;
            otherChar = COMP_CHAR;
        } else {
            ourChar = COMP_CHAR;
            otherChar = USER_CHAR;
        }
        int k = i + Math.min(SIZE - 1 - i, j);
        int n = j - Math.min(SIZE - 1 - i, j);
        while (k >= 0 && n < SIZE) {
            if (field[k][n] == ourChar) {
                countOfX++;
            } else if (field[k][n] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && SIZE >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == SYMB_TO_WIN - 2) {
                        if (rand.nextInt(2) == 0) {
                            compXY[0] = x;
                            compXY[1] = y;
                        } else {
                            compXY[0] = k;
                            compXY[1] = n;
                        }
                        return true;
                    }
                }
                x = k;
                y = n;
                isDanger = 1;
            } else if (field[k][n] == otherChar) {
                countOfX = 0;
                isDanger = 0;
            }
            if (countOfX == SYMB_TO_WIN - 1 && x >= 0) {
                compXY[0] = x;
                compXY[1] = y;
                return true;
            }
            k--;
            n++;
        }

        return false;
    }

    public static boolean checkOurPrevWin(int[] compXY) {
        for (int i = 0; i < SIZE; i++) {
            if (checkPrevWinLine(compXY, i, false)) {
                return true;
            }
        }
        for (int j = 0; j < SIZE; j++) {
            if (checkPrevWinCol(compXY, j, false)) {
                return true;
            }
        }
        for (int i = 0; i <= SIZE - SYMB_TO_WIN; i++) {
            if (checkPrevWinLeftDiag(compXY, i, 0, false)) {
                return true;
            }
            if (i > 0) {
                if (checkPrevWinLeftDiag(compXY, 0, i, false)) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= SIZE - SYMB_TO_WIN; i++) {
            if (checkPrevWinRightDiag(compXY, 0, SIZE-1-i, false)) {
                return true;
            }
            if (i > 0) {
                if (checkPrevWinRightDiag(compXY, i, SIZE-1, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int[] compMove(int i, int j) {
        int[] compXY = new int[2];
        //может ли компьютер выиграть за один ход
        if (!checkOurPrevWin(compXY)) {
            //проверим i-ю строку, имеет ли она предвыигрышное состояние, если да, то заблокируем его
            if (!checkPrevWinLine(compXY, i, true)) {
                //иначе проверим j-й столбец
                if (!checkPrevWinCol(compXY, j, true)) {
                    //иначе проверим прямую диагональ
                    if (!checkPrevWinLeftDiag(compXY, i, j, true)) {
                        //иначе проверим обратную диагональ
                        if (!checkPrevWinRightDiag(compXY, i, j, true)) {
                            int x, y;
                            do {
                                x = rand.nextInt(SIZE);
                                y = rand.nextInt(SIZE);
                            } while (!isValidMove(x, y));
                            compXY[0] = y;
                            compXY[1] = x;
                        }
                    }
                }
            }
        }

        System.out.println("Ход компьютера: " + (compXY[1] + 1) + ", " + (compXY[0] + 1));
        field[compXY[0]][compXY[1]] = COMP_CHAR;
        countOfMoves++;
        return compXY;
    }

    public static int[] userMove() {
        int x = 0, y = 0;
        while (!isValidMove(x - 1, y - 1)) {
            System.out.println("Введите координаты x и y от 1 до " + SIZE);
            if (sc.hasNextInt()) {
                x = sc.nextInt();
            } else {
                sc.nextLine();
                continue;
            }
            if (sc.hasNextInt()) {
                y = sc.nextInt();
            } else {
                sc.nextLine();
                continue;
            }
        }
        field[y - 1][x - 1] = USER_CHAR;
        countOfMoves++;
        return new int[]{y - 1, x - 1};
    }

    public static boolean isValidMove(int x, int y) {
        if (x < 0 || x > SIZE - 1 || y < 0 || y > SIZE - 1) {
            return false;
        }
        if (field[y][x] == EMPTY_CHAR) {
            return true;
        }
        return false;
    }

    public static void initField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY_CHAR;
            }
        }
        countOfMoves = 0;
    }

    public static void printField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
