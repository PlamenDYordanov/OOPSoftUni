package christmasPastryShop.entities.delicacies;

public class Gingerbread extends BaseDelicacy {
    private static final double DEFAULT_PORTION = 200;

    public Gingerbread(String name, double price) {
        super(name, DEFAULT_PORTION, price);
    }
}
