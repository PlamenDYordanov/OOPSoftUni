package solid.products;

import solid.products.Interfaces.Drink;
import solid.products.Interfaces.Product;

import java.util.List;

public class Coke implements  Product, Drink {
    private List<Drink> product;
    private static final double CALORIES_PER_100_GRAMS = 44.0;
    private static final double DENSITY = 0.6;
    private double milliliters;

    public Coke(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }

    @Override
    public double calculateCalories() {
        double grams = getMilliliters() * DENSITY;
        double sum = (CALORIES_PER_100_GRAMS / 100) * grams;
        return sum;
    }

    @Override
    public double getLitters() {
        double litters = (getMilliliters() * DENSITY) / 1000;
        return litters * DENSITY;
    }

    @Override
    public double getTotalWeight() {
        double totalAmountOfWeight = 0;
        double sum = product.stream().mapToDouble(Drink::getLitters).sum();
        totalAmountOfWeight += sum;
        return totalAmountOfWeight;
    }

    @Override
    public double getAverageWeight() {
        double sum = product.stream().mapToDouble(Drink::getLitters).sum();
        return sum / product.size();
    }
}
