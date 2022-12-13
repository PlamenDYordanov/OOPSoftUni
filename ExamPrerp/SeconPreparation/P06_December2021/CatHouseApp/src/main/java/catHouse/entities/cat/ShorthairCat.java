package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private static final  int DEFAULT_KG = 7;
    private static  final  int DEFAULT_INCREASING_KG_AFTER_EATING = 1;
    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.setKilograms(DEFAULT_KG);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + DEFAULT_INCREASING_KG_AFTER_EATING);
    }
}
