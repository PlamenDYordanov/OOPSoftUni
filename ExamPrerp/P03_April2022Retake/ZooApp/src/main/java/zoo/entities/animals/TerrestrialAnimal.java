package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{
    private static final double DEFAULT_KG = 5.50;
    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, DEFAULT_KG, price);
    }

    @Override
    public void eat() {
        setKg(getKg() + 5.70);
    }
}
