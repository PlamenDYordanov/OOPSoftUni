package restaurant;

import java.math.BigDecimal;

public class Dessert extends Food{
    private double calories;
    public Dessert(String name, BigDecimal price, double gram, double calories) {
        super(name, price, gram);
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }
}
