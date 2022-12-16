package bakery.entities.bakedFoods;

import bakery.entities.bakedFoods.interfaces.BakedFood;

public class Bread extends BaseFood {
    private static final double DEFAULT_PORTION = 200;
    public Bread(String name, double price) {
        super(name, DEFAULT_PORTION, price);
    }
}
