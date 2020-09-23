package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public int[][] field;
    public int size;
    public int symbToWin;
    public final int EMPTY_CHAR = 0;
    public final int USER_CHAR = 1;
    public final int COMP_CHAR = 2;
    public int countOfMoves; //для определения ничьи
    public final boolean LEVEL_UP = true; //для выключения более сложной игры компьютера (не завершено)
    public int[] lastMove = {-1, -1};
    public int state = 0; //1-ничья; 2-вы выиграли; 3-вы проиграли

    public Random rand = new Random();

    public Game(int size) {
        this.size = size;
        symbToWin = (size == 3) ? 3 : 4;
        initField();
    }

    public void gameIteration(int i, int j) {
        userMove(i, j);
        lastMove[0] = -1;
        lastMove[1] = -1;
        if (checkWin(i, j, USER_CHAR)) {
            state = 2;
        } else if (countOfMoves == size * size) {
            state = 1;
        } else {
            int[] compXY = compMove(i, j);
            lastMove[0] = compXY[0];
            lastMove[1] = compXY[1];
            if (checkWin(compXY[0], compXY[1], COMP_CHAR)) {
                state = 3;
            } else if (countOfMoves == size * size) {
                state = 1;
            }
        }
    }

    public void userMove(int i, int j) {
        if (isValidMove(i, j)) {
            field[i][j] = USER_CHAR;
            countOfMoves++;
        }
    }

    public boolean isValidMove(int i, int j) {
        if (field[i][j] == EMPTY_CHAR) {
            return true;
        }
        return false;
    }

    public void initField() {
        field = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = EMPTY_CHAR;
            }
        }
        countOfMoves = 0;
    }

    public boolean checkWin(int i, int j, int symb) {
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

    public boolean checkWinLeftDiag(int i, int j, int symb) {
        int k = i - Math.min(i, j);
        int n = j - Math.min(i, j);
        int countOfMatches = 0;
        while (k < this.size && n < this.size) {
            countOfMatches = (field[k][n] == symb) ? (countOfMatches + 1) : 0;
            if (countOfMatches == symbToWin) {
                return true;
            }
            k++;
            n++;
        }
        return false;
    }

    public boolean checkWinRightDiag(int i, int j, int symb) {
        int k = i + Math.min(this.size - 1 - i, j);
        int n = j - Math.min(this.size - 1 - i, j);
        int countOfMatches = 0;
        while (k >= 0 && n < this.size) {
            countOfMatches = (field[k][n] == symb) ? (countOfMatches + 1) : 0;
            if (countOfMatches == symbToWin) {
                return true;
            }
            k--;
            n++;
        }
        return false;
    }

    public boolean checkWinLine(int i, int j, int vx, int vy, int symb) {
        int countOfMatches = 0;
        for (int k = 0; k < this.size; k++) {
            //при проверке i-й строки будет field[i][k], при проверке j-го столбца будет field[k][j]
            countOfMatches = (field[i * vx + k * vy][k * vx + j * vy] == symb) ? (countOfMatches + 1) : 0;
            if (countOfMatches == symbToWin) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPrevWinLine(int[] compXY, int i, boolean isUser) {
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
        for (int k = 0; k < this.size; k++) {
            if (field[i][k] == ourChar) {
                countOfX++;
            } else if (field[i][k] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && this.size >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == symbToWin - 2) {
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
            if (countOfX == symbToWin - 1 && y >= 0) {
                compXY[0] = i;
                compXY[1] = y;
                return true;
            }
        }
        return false;
    }

    public boolean checkPrevWinCol(int[] compXY, int j, boolean isUser) {
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
        for (int k = 0; k < this.size; k++) {
            if (field[k][j] == ourChar) {
                countOfX++;
            } else if (field[k][j] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && this.size >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == symbToWin - 2) {
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
            if (countOfX == symbToWin - 1 && x >= 0) {
                compXY[0] = x;
                compXY[1] = j;
                return true;
            }
        }
        return false;
    }

    public boolean checkPrevWinLeftDiag(int[] compXY, int i, int j, boolean isUser) {
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
        while (k < this.size && n < this.size) {
            if (field[k][n] == ourChar) {
                countOfX++;
            } else if (field[k][n] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && this.size >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == symbToWin - 2) {
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
            if (countOfX == symbToWin - 1 && x >= 0) {
                compXY[0] = x;
                compXY[1] = y;
                return true;
            }
            k++;
            n++;
        }

        return false;
    }

    public boolean checkPrevWinRightDiag(int[] compXY, int i, int j, boolean isUser) {
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
        int k = i + Math.min(this.size - 1 - i, j);
        int n = j - Math.min(this.size - 1 - i, j);
        while (k >= 0 && n < this.size) {
            if (field[k][n] == ourChar) {
                countOfX++;
            } else if (field[k][n] == EMPTY_CHAR) {
                //предотвратим комбинацию . x x x .
                if (LEVEL_UP && this.size >= 5 && isUser) {
                    if (isDanger == 1 && countOfX == symbToWin - 2) {
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
            if (countOfX == symbToWin - 1 && x >= 0) {
                compXY[0] = x;
                compXY[1] = y;
                return true;
            }
            k--;
            n++;
        }

        return false;
    }

    public boolean checkOurPrevWin(int[] compXY) {
        for (int i = 0; i < this.size; i++) {
            if (checkPrevWinLine(compXY, i, false)) {
                return true;
            }
        }
        for (int j = 0; j < this.size; j++) {
            if (checkPrevWinCol(compXY, j, false)) {
                return true;
            }
        }
        for (int i = 0; i <= this.size - symbToWin; i++) {
            if (checkPrevWinLeftDiag(compXY, i, 0, false)) {
                return true;
            }
            if (i > 0) {
                if (checkPrevWinLeftDiag(compXY, 0, i, false)) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= this.size - symbToWin; i++) {
            if (checkPrevWinRightDiag(compXY, 0, this.size - 1 - i, false)) {
                return true;
            }
            if (i > 0) {
                if (checkPrevWinRightDiag(compXY, i, this.size - 1, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int[] compMove(int i, int j) {
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
                                x = rand.nextInt(this.size);
                                y = rand.nextInt(this.size);
                            } while (!isValidMove(x, y));
                            compXY[0] = y;
                            compXY[1] = x;
                        }
                    }
                }
            }
        }

        //System.out.println("Ход компьютера: " + (compXY[1] + 1) + ", " + (compXY[0] + 1));
        field[compXY[0]][compXY[1]] = COMP_CHAR;
        countOfMoves++;
        return compXY;
    }


}
