package restaurant.entities.drinks;

public class Smoothie extends BaseBeverage{
    private static final  double DEFAULT_PRICE = 4.50;

    public Smoothie(String name, int counter, String brand) {
        super(name, counter, DEFAULT_PRICE, brand);
    }
}
