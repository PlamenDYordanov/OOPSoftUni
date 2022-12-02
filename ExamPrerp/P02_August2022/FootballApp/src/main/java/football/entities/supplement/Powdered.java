package football.entities.supplement;

public class Powdered extends BaseSupplement {
    private static final int POWDER_ENERGY = 120;
    private static final double POWDER_PRICE = 120;

    public Powdered() {
        super(POWDER_ENERGY, POWDER_PRICE);
    }
}
