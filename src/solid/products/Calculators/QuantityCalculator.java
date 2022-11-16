package solid.products.Calculators;

import solid.Printer;
import solid.products.Interfaces.Drink;
import solid.products.Interfaces.Food;

import java.util.List;

public class QuantityCalculator {
    private static final String TOTAL_WEIGHT = "Sum: %f%n";
    private static final String AVERAGE_WEIGHT = "Average: %f%n";
    Printer printer;

    public QuantityCalculator() {
        this.printer = new Printer();
    }

    public double getTotalKilosFood(List<Food> product) {
        double sum = product.stream().mapToDouble(Food::getKilograms).sum();
        this.printer.printTotalWeight(TOTAL_WEIGHT, sum);
        return sum;
    }

    public double getAverageKilosFood(List<Food> product) {
        double sum = product.stream().mapToDouble(Food::getKilograms).sum();
       double average = sum / product.size();
       this.printer.printAverageWeight(AVERAGE_WEIGHT,average);
        return average;
    }
    public double getTotalLittersDrinks(List<Drink> product) {
        double sum = product.stream().mapToDouble(Drink::getLitters).sum();
        this.printer.printTotalWeight(TOTAL_WEIGHT, sum);
        return  sum;
    }
    public double getAverageLittersDrinks(List<Drink> product) {
        double sum = product.stream().mapToDouble(Drink::getLitters).sum();
        double average = sum / product.size();
        this.printer.printAverageWeight(AVERAGE_WEIGHT, average);
        return average;
    }
}
