package solid.products.Calculators;

import solid.Printer;
import solid.products.Interfaces.Product;

import java.util.List;

public class CalorieCalculator {

    private Printer printer;
    private static final String SUM_FORMAT = "Sum: %f%n";
    private static final String AVERAGE_FORMAT = "Average: %f%n";

    public CalorieCalculator() {
        printer = new Printer();
    }

    public double sum(List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            double calories = product.calculateCalories();

            sum += calories;
        }
        this.printer.printCalories(SUM_FORMAT, sum);
        return sum;
    }

    public double average(List<Product> products) {
        double average = sum(products) / products.size();
        printer.printCalories(AVERAGE_FORMAT, average);
        return average;
    }


}
