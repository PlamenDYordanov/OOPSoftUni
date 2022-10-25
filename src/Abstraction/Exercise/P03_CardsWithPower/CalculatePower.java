package Abstraction.Exercise.P03_CardsWithPower;

public class CalculatePower {

    public static int calculationOfPower(Card rank, SuitPower power){

        return rank.getPower() + power.getPower();
    }
}
