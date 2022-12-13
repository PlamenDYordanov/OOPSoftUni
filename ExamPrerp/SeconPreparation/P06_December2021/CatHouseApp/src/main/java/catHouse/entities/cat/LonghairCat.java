package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    private static final  int DEFAULT_KG = 9;
    private static  final  int DEFAULT_INCREASING_KG_AFTER_EATING = 3;
    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(DEFAULT_KG);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + DEFAULT_INCREASING_KG_AFTER_EATING);
    }
}
