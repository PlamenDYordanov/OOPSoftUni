package ExceptionAndErrorHandling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P03_EnterNumbers {
    private static int start = 1;
    private static final int lastNumber = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> listOfNumbers = new ArrayList<>();
        while (listOfNumbers.size() < 10) {
            String input = scanner.nextLine();
            readNumbers(input, start, lastNumber, listOfNumbers);
        }
        System.out.println(String.join(", ",listOfNumbers));
    }

    private static void readNumbers(String input, int startNumber, int finalNumber, List<String> listOfNumbers) {
        try {
            if (Integer.parseInt(input) > startNumber && Integer.parseInt(input) < 100) {
                listOfNumbers.add(input);
                start = Integer.parseInt(input);
            } else {
                System.out.printf("Your number is not in range %d - %d!%n", startNumber, finalNumber);
            }
        } catch (NumberFormatException exception) {
            System.out.println("Invalid Number!");
        }
    }
}
