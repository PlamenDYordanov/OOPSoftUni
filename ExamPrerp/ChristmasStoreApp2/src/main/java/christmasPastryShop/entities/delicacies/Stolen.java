package christmasPastryShop.entities.delicacies;

public class Stolen extends BaseDelicacy{

    private final static double InitialStolenPortion = 250.0;

    public Stolen(String name, double price) {
        super(name, InitialStolenPortion, price);
    }
}
