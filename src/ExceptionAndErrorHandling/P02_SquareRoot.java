package ExceptionAndErrorHandling;

import java.util.Scanner;

public class P02_SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        try {
            if (Integer.parseInt(input) > 0) {
                System.out.printf("%.2f%n",Math.sqrt(Integer.parseInt(input)));
            }else {
                System.out.println("Invalid");
            }
        } catch (NumberFormatException exception) {
            System.out.println("Invalid");

        } finally {
            System.out.println("Goodbye");
        }
    }
}
