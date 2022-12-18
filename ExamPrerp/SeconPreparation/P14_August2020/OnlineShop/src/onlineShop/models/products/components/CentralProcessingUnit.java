package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

public class CentralProcessingUnit extends BaseComponent {
    private static final double DEFAULT_MULTIPLIER = 1.25;
    public CentralProcessingUnit(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * DEFAULT_MULTIPLIER, generation);

    }

}
