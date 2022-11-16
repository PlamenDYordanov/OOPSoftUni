package solid.products;

import solid.products.Interfaces.Food;
import solid.products.Interfaces.Product;

import java.util.List;

public class Chips implements Product, Food {
    private List<Food> product;
    private static final double CALORIES_PER_100_GRAMS = 529.00;
    private double grams;

    public Chips(double grams) {
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }

    @Override
    public double calculateCalories() {
        double sum = (CALORIES_PER_100_GRAMS / 100) * grams;
        return sum;
    }


    @Override
    public double getKilograms() {
        return grams / 1000;
    }

    @Override
    public double totalKilos () {
        double totalAmountOfWeight = 0;
        double sum = product.stream().mapToDouble(Food::getKilograms).sum();
        totalAmountOfWeight += sum;
        return totalAmountOfWeight;
    }

    @Override
    public double averageKilos() {
        double sum = product.stream().mapToDouble(Food::getKilograms).sum();
        return sum / product.size();
    }
}
