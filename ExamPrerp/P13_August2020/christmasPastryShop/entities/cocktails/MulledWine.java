package christmasPastryShop.entities.cocktails;

public class MulledWine extends BaseCocktail{
    private final static double DEFAULT_PRICE = 3.50;

    public MulledWine(String name, int size, String brand) {
        super(name, size, DEFAULT_PRICE, brand);
    }
}
