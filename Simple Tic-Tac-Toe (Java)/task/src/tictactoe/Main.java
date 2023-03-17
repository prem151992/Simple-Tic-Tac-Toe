package tictactoe;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void readInput(int counter, int[][] tickTacToeArray) {
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                Scanner scanner = new Scanner(System.in);

                int firstIndex = scanner.nextInt();
                int secondIndex = scanner.nextInt();

                if (firstIndex > 3 || secondIndex > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (tickTacToeArray[firstIndex-1][secondIndex-1] == 1 || tickTacToeArray[firstIndex-1][secondIndex-1] == 2) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (counter % 2 != 0) {
                        tickTacToeArray[firstIndex - 1][secondIndex - 1] = 1;
                    } else if (counter % 2 == 0) {
                        tickTacToeArray[firstIndex - 1][secondIndex - 1] = 2;
                    }
                    keepRunning = false;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }

    }

    public static void printTikTacToe(int[][] tickTacToeArray) {
        System.out.println("---------");
        for (int i = 0; i < tickTacToeArray.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < tickTacToeArray[i].length; j++) {
                if (tickTacToeArray[i][j] == 1) {
                    System.out.print('X' + " ");
                } else if (tickTacToeArray[i][j] == 2) {
                    System.out.print('O' + " ");
                } else if (tickTacToeArray[i][j] == 100) {
                    System.out.print(' ' + " ");
                }

            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String computeWinner(int[][] tickTacToeArray) {
        int[] row = new int[3];
        int[] column = new int[3];
        int[] diagonal = new int[2];
        int xCount = 0;
        int oCount = 0;
        int emptySpaces = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (tickTacToeArray[i][j]) {
                    case 1:
                        xCount +=1;
                        break;
                    case 2:
                        oCount +=1;
                        break;
                    case 100:
                        emptySpaces +=1;
                        break;
                    default:
                        break;
                }
                row[i] += tickTacToeArray[i][j];
                column[j] += tickTacToeArray[i][j];
                if (i == j) {
                    diagonal[0] += tickTacToeArray[i][j];
                    if (i == 1 && j == 1) {
                        diagonal[1] += tickTacToeArray[i][j];
                    }
                } else if (Math.abs(i - j) == 2) {
                    diagonal[1] += tickTacToeArray[i][j];
                }
            }
        }

        boolean isXWinner = false;
        boolean is0Winner = false;

        for (int i = 0; i < row.length; i++) {
            if (row[i] == 3) {
                isXWinner = true;
            } else if (row[i] == 6) {
                is0Winner = true;
            }
        }

        for (int i = 0; i < column.length; i++) {
            if (column[i] == 3) {
                isXWinner = true;
            } else if (column[i] == 6) {
                is0Winner = true;
            }
        }

        for (int i = 0; i < diagonal.length; i++) {
            if (diagonal[i] == 3) {
                isXWinner = true;
            } else if (diagonal[i] == 6) {
                is0Winner = true;
            }
        }

        String result = "";
        if (isXWinner && is0Winner) {
            result = "Impossible";
        } else if (isXWinner) {
            result = "X wins";
        } else if (is0Winner) {
            result = "O wins";
        } else if (Math.abs(xCount - oCount) == 2) {
            result = "Impossible";
        } else if (xCount <= 3 || oCount <= 3) {
            result = "Game not finished";
        } else {
            result = "Draw";
        }
        return result;
    }
    public static void main(String[] args) {

//        System.out.println(inputStr);
        int[][] tickTacToeArray = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tickTacToeArray[i][j] = 100;
                }
            }

        printTikTacToe(tickTacToeArray);
        int counter = 1;

        String winner;

        while (true) {
            readInput(counter, tickTacToeArray);
            printTikTacToe(tickTacToeArray);
            counter +=1;
            winner = computeWinner(tickTacToeArray);

            if (counter > 9 || winner.equals("X wins") || winner.equals("O wins")) {
                break;
            }

        }
        System.out.println(winner);
    }
}
