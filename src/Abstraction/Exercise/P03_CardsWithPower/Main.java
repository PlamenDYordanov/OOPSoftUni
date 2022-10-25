package Abstraction.Exercise.P03_CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String card = scanner.nextLine();
        String suit = scanner.nextLine();
        Card curCard = Card.valueOf(card);
        SuitPower curSuit = SuitPower.valueOf(suit);
        System.out.printf("Card name: %s of %s; Card power: %d%n",curCard, curSuit, CalculatePower.calculationOfPower(curCard, curSuit));

    }
}
