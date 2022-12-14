package aquarium.entities.decorations;

public class Plant extends BaseDecoration{
    private static final double DEFAULT_PRICE = 10;
    private static final int DEFAULT_COMFORT = 5;
    public Plant() {
        super(DEFAULT_COMFORT, DEFAULT_PRICE);
    }
}
