package catHouse.entities.toys;

public class Mouse extends BaseToy{
    private static final double DEFAULT_PRICE = 15;
    private static final int DEFAULT_SOFTNESS = 5;
    public Mouse() {
        super(DEFAULT_SOFTNESS, DEFAULT_PRICE);
    }
}
