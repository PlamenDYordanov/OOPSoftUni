package restaurant.entities.drinks;

import restaurant.entities.drinks.BaseBeverage;

public class Fresh extends BaseBeverage {
    private static final  double DEFAULT_PRICE = 4.50;
    public Fresh(String name, int counter, String brand) {
        super(name, counter, DEFAULT_PRICE, brand);
    }
}
