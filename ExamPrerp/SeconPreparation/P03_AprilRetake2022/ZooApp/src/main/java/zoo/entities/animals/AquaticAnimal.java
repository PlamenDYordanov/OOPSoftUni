package zoo.entities.animals;

import zoo.entities.foods.BaseFood;

public class AquaticAnimal extends BaseAnimal {
    private static  final double DEFAULT_KG = 2.50;
    private static  final double DEFAULT_INCREASE_KG = 7.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, DEFAULT_KG, price);
    }

    @Override
    public void eat() {
        setKg(getKg() + DEFAULT_INCREASE_KG);
    }
}
