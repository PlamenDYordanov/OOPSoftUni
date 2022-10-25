package Abstraction.Exercise.P01_CardSuit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Card Suits:");
        for (Card value : Card.values()) {
            System.out.println("Ordinal value: " + value.ordinal() + "; " + "Name value:" + " " + value.name());
        }
    }
}
