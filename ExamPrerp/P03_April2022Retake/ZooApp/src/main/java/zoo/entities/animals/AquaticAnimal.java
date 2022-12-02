package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal{
    private static final double DEFAULT_KG = 2.50;
    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, DEFAULT_KG, price);
    }

    @Override
    public void eat() {
        setKg(super.getKg() + 7.50);
    }
}
