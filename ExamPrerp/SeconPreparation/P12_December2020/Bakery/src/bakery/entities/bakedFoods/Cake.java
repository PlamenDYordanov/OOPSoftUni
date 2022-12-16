package bakery.entities.bakedFoods;

public class Cake extends BaseFood{
    private static final double DEFAULT_PORTION = 245;
    public Cake(String name, double price) {
        super(name, DEFAULT_PORTION, price);
    }
}
