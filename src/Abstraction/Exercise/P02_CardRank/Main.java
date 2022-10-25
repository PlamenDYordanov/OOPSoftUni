package Abstraction.Exercise.P02_CardRank;



public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        for (Card value : Card.values()) {
            System.out.println("Ordinal value: " + value.ordinal() + "; " + "Name value:" + " " + value.name());
        }
    }
}
