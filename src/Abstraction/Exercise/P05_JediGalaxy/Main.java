package Abstraction.Exercise.P05_JediGalaxy;

import java.util.Scanner;

public class Main {
    private static int peterRow;
    private static int peterCol;
    private static int evilRow;
    private static int evilCol;
    private static int totalStars;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dimension = scanner.nextLine().split("\\s+");
        int[][] matrix = new int[Integer.parseInt(dimension[0])][Integer.parseInt(dimension[1])];
        fillMatrix(matrix);

        String input = scanner.nextLine();
        while (!input.equals("Let the Force be with you")) {
            String[] peterRowCol = input.split("\\s+");
            peterRow = Integer.parseInt(peterRowCol[0]);
            peterCol = Integer.parseInt(peterRowCol[1]);
            String nextInputs = scanner.nextLine();
            String[] evilRowCol = nextInputs.split("\\s+");
            evilRow = Integer.parseInt(evilRowCol[0]);
            evilCol = Integer.parseInt(evilRowCol[1]);
            evilNumber(matrix, evilRow, evilCol);
            collectStars(matrix, peterRow, peterCol);
            input = scanner.nextLine();
        }
        System.out.println(totalStars);
    }

    private static void collectStars(int[][] matrix, int peterRow, int peterCol) {
        int currentRow = peterRow - 1;
        int currentCol = peterCol - 1;
        boolean isEnough = false;
        if (isInBound(matrix, currentRow, currentCol)) {
            collect(matrix, currentRow, currentCol);
        } else {
            if (currentCol < 0) {
                currentCol = 0;
            }
            if (currentRow < 0) {
                currentRow = 0;
            }
            if (currentCol > matrix[currentRow].length - 1) {
                currentCol = matrix[currentRow].length - 1;
            }
            if (currentRow > matrix.length - 1) {
                currentRow = matrix.length - 1;
            }
            collect(matrix, currentRow, currentCol);
        }
    }

    private static void collect(int[][] matrix, int currentRow, int currentCol) {
        boolean isEnough = false;
        for (int row = currentRow; row >= 0; row--) {
            if (isInBound(matrix, currentRow, row)) {
                totalStars += matrix[row][currentCol];
            } else {
                isEnough = true;
                break;
            }
            currentCol++;
        }
    }

    private static void evilNumber(int[][] matrix, int evilRow, int evilCol) {
        int currentRow = evilRow - 1;
        int currentCol = evilCol - 1;
        boolean isEnough = false;
        if (isInBound(matrix, currentRow, currentCol)) {
            removeEvilNumbers(matrix, currentRow, currentCol);
        } else {
            if (currentCol < 0) {
                currentCol = 0;
            }
            if (currentRow < 0) {
                currentRow = 0;
            }
            if (currentCol > matrix[currentRow].length - 1) {
                currentCol = matrix[currentRow].length - 1;
            }
            if (currentRow > matrix.length - 1) {
                currentRow = matrix.length - 1;
            }
            removeEvilNumbers(matrix, currentRow, currentCol);
        }
    }

    private static void removeEvilNumbers(int[][] matrix, int currentRow, int currentCol) {
        boolean isEnough = false;

        for (int col = currentCol; col >= 0; col--) {
            if (isInBound(matrix, currentRow, col)) {
                matrix[currentRow][col] = 0;
            } else {
                isEnough = true;
                break;
            }
            currentRow--;
        }
    }


    private static boolean isInBound(int[][] matrix, int evilRow, int evilCol) {
        return evilRow >= 0 && evilRow < matrix.length && evilCol >= 0 && evilCol < matrix[evilRow].length;
    }

    private static void fillMatrix(int[][] matrix) {
        int value = 0;
        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = value;
                value++;
            }
        }
    }
}
