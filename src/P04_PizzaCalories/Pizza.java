package P04_PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        setName(name);
        setToppings(numberOfToppings);
        this.toppings = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.length() < 1 || name.length() >= 15 || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    private void setToppings(int number) {
        if (number < 0 || number > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
    }

    public String getName() {
        return name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void addTopping(Topping topping) {
        if (toppings.size() > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        } else {
            this.toppings.add(topping);
        }
    }

    public double getOverallCalories() {
        double totalToppingCalories = 0;
        for (Topping topping : this.toppings) {
            totalToppingCalories += topping.calculateCalories();
        }
        double doughCalories = dough.calculateCalories();
        return totalToppingCalories + doughCalories;
    }
}
