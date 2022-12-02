package fairyShop.models;

public class Sleepy extends BaseHelper{
    private static final int DEFAULT_ENERGY = 50;
    private static final int DEFAULT_DECREASE_ENERGY = 15;
    public Sleepy(String name) {
        super(name, DEFAULT_ENERGY);
    }

    @Override
    public void work() {
        setEnergy(Math.max(0 , getEnergy() - DEFAULT_ENERGY));
    }
}
