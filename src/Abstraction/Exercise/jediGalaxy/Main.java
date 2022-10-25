package workingWithAbstraction.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimension = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = dimension[0];
        int col = dimension[1];

        int[][] matrix = new int[row][col];

        int value = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = value++;
            }
        }
        String command = scanner.nextLine();
        long totalStars = 0;

        while (!command.equals("Let the Force be with you")) {
            int[] playerDimension = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evilDimension = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startRowEvil = evilDimension[0];
            int startColEvil = evilDimension[1];

            while (startRowEvil >= 0 && startColEvil >= 0) {
                if (startRowEvil < matrix.length && startColEvil < matrix[0].length) {
                    matrix[startRowEvil][startColEvil] = 0;
                }
                startRowEvil--;
                startColEvil--;
            }

            int startRowOfPlayer = playerDimension[0];
            int startColOfPlayer = playerDimension[1];

            while (startRowOfPlayer >= 0 && startColOfPlayer < matrix[1].length) {
                if (startRowOfPlayer < matrix.length && startColOfPlayer >= 0 && startColOfPlayer < matrix[0].length) {
                    totalStars += matrix[startRowOfPlayer][startColOfPlayer];
                }

                startColOfPlayer++;
                startRowOfPlayer--;
            }

            command = scanner.nextLine();
        }

        System.out.println(totalStars);


    }
}
