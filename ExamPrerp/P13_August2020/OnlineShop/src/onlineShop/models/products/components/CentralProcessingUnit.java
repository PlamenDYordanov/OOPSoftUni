package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;

public class CentralProcessingUnit extends BaseComponent {

    private static final double DEFAULT_MULTIPLIER_FOR_OVERALL_PERFORMANCE = 1.25;

    public CentralProcessingUnit(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance, generation);
        multiplyOverallPerformance(overallPerformance);
    }

    @Override
    protected void multiplyOverallPerformance(double performance) {
        performance *= DEFAULT_MULTIPLIER_FOR_OVERALL_PERFORMANCE;
    }

}
