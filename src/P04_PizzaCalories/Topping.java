package P04_PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
       setToppingType(toppingType);
       setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        if (toppingType.equals("Cheese") || toppingType.equals("Meat") || toppingType.equals("Veggies") || toppingType.equals("Sauce")){
            this.toppingType = toppingType;
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
        double totalToppingCalories = 0;
        if (this.toppingType.equals("Meat")){
            totalToppingCalories += (this.weight * 2) * 1.2;
        }else if (this.toppingType.equals("Veggies")){
            totalToppingCalories += (this.weight * 2) * 0.8;
        }else if (this.toppingType.equals("Cheese")){
            totalToppingCalories += (this.weight * 2) * 1.1;
        }else if (this.toppingType.equals("Sauce")){
            totalToppingCalories += (this.weight * 2) * 0.9;
        }
        return totalToppingCalories;
    }
}

