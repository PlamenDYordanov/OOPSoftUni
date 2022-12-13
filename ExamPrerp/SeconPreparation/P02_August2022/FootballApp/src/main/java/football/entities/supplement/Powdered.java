package football.entities.supplement;

public class Powdered extends BaseSupplement{
    private static final double DEFAULT_PRICE = 15;
    private static final int DEFAULT_ENERGY = 120;
    public Powdered() {
        super(DEFAULT_ENERGY, DEFAULT_PRICE);
    }
}
