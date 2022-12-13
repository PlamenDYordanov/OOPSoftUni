package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{
    private static  final double DEFAULT_KG = 5.50;
    private static  final double DEFAULT_INCREASE_KG = 5.70;
    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, DEFAULT_KG, price);
    }

    @Override
    public void eat() {
        setKg(getKg() + DEFAULT_INCREASE_KG);
    }
}
