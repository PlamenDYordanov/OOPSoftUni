package restaurant.entities.healthyFoods;

public class Salad extends Food{
    private static final double DEFAULT_PORTION = 150.00;

    public Salad(String name, double price) {
        super(name, DEFAULT_PORTION, price);
    }
}
