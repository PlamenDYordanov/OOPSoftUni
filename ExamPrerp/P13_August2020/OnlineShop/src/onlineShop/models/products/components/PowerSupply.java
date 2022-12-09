package onlineShop.models.products.components;

public class PowerSupply extends BaseComponent{
    private static final double DEFAULT_MULTIPLIER_FOR_OVERALL_PERFORMANCE = 1.05;
    public PowerSupply(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance* DEFAULT_MULTIPLIER_FOR_OVERALL_PERFORMANCE, generation);

    }

}
