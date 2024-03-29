package Encapsulation.Exercise.P04_PizzaCalories;

public enum ToppingEnum {
    Meat(1.2),
    Veggies(0.8),
    Cheese(1.1),
    Sauce(0.9);

    private double modifier;

    ToppingEnum(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }

}
