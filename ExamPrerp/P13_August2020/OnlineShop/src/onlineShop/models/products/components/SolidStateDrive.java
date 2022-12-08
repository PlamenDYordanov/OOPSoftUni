package onlineShop.models.products.components;

public class SolidStateDrive extends BaseComponent{
    private static final double DEFAULT_MULTIPLIER_FOR_OVERALL_PERFORMANCE = 1.20;
    public SolidStateDrive(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance, generation);
        multiplyOverallPerformance(overallPerformance);
    }

    @Override
    protected void multiplyOverallPerformance(double performance) {
        performance *= DEFAULT_MULTIPLIER_FOR_OVERALL_PERFORMANCE;
    }
}
