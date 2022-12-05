package restaurant.entities.healthyFoods;

public class VeganBiscuits extends Food{
    private static final double DEFAULT_PORTION = 205.00;
    public VeganBiscuits(String name, double price) {
        super(name,DEFAULT_PORTION, price );
    }
}
