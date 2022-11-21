package ExceptionAndErrorHandling;

import java.util.Scanner;

public class P01_NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] rangeNumber = scanner.nextLine().split(" ");
        int startNumber = Integer.parseInt(rangeNumber[0]);
        int finalNumber = Integer.parseInt(rangeNumber[1]);
        System.out.println("Range: " + "[" + startNumber + "..." + finalNumber + "]");
        readInputFromConsole(scanner, startNumber, finalNumber);

    }

    private static void readInputFromConsole(Scanner scanner, int startNumber, int finalNumber) {
        while (true){
            String  input = scanner.nextLine();
            try {
               if (Integer.parseInt(input) >= startNumber && Integer.parseInt(input) <= finalNumber){
                   System.out.printf("Valid number: %d%n", Integer.parseInt(input));
                   return;
               }
            }catch (NumberFormatException exception){
            }
            System.out.printf("Invalid number: %s%n", input);
        }
    }
}
