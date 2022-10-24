package Abstraction.P02_PointOfRectange;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] coordinates = scanner.nextLine().split("\\s+");

        int bottomLeftX = Integer.parseInt(coordinates[0]);
        int bottomLeftY = Integer.parseInt(coordinates[1]);
        int topRightX = Integer.parseInt(coordinates[2]);
        int topRightY = Integer.parseInt(coordinates[3]);

        int numberOfPoints = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= numberOfPoints ; i++) {
            String[] dimension = scanner.nextLine().split("\\s+");

            int pointX = Integer.parseInt(dimension[0]);
            int pointY = Integer.parseInt(dimension[1]);
            System.out.println(Contains(pointX, pointY, bottomLeftX, bottomLeftY, topRightX, topRightY));
        }
    }

    private static boolean Contains(int pointX, int pointY, int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        return pointX >= bottomLeftX && pointX <= topRightX && pointY >= bottomLeftY && pointY <= topRightY;
    }
}
