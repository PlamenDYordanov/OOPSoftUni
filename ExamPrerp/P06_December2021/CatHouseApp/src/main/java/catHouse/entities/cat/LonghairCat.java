package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    private int kilogramsDefault = 9;
    private static final int DEFAULT_INCREASE_KILOGRAMS = 3;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        super.eating();
        setKilograms(kilogramsDefault += DEFAULT_INCREASE_KILOGRAMS);
    }
}
