package christmasPastryShop.entities.delicacies;

import christmasPastryShop.entities.delicacies.BaseDelicacy;

public class Stolen extends BaseDelicacy {
    private final static double DEFAULT_PORTION = 250;
    public Stolen(String name, double price) {
        super(name, DEFAULT_PORTION, price);
    }
}
