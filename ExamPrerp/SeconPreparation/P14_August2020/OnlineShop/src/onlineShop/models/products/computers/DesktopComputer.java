package onlineShop.models.products.computers;

import onlineShop.models.products.Product;

import java.util.OptionalDouble;

public class DesktopComputer extends BaseComputer{
    private static final double OVERALL_PERFORMANCE = 15;
    public DesktopComputer(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, OVERALL_PERFORMANCE);
    }


}
