package christmasPastryShop.entities.cocktails;

public class Hibernation extends BaseCocktail{
    private static final double DEFAULT_PRICE = 4.50;
    public Hibernation(String name, int size, String brand) {
        super(name, size, DEFAULT_PRICE, brand);
    }
}
