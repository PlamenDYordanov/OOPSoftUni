package Encapsulation.Exercise.P04_PizzaCalories;

import java.util.Arrays;

public class Topping {
    private ToppingEnum toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
       setToppingType(toppingType);
       setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        boolean toppingIsExist = Arrays.stream(ToppingEnum.values()).anyMatch(e -> e.name().equals(toppingType));
        if (toppingIsExist){
            this.toppingType = ToppingEnum.valueOf(toppingType);
        }else {
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50 ) {
            throw new IllegalArgumentException(this.toppingType +" weight should be in the range [1..50].");
        }
        this.weight = weight;
    }
    public double calculateCalories() {

        return 2 * weight * getToppingModifier();
    }
    private double getToppingModifier() {
        return toppingType.getModifier();
    }
}

