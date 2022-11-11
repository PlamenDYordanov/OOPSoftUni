package Polymorphism.Exercise.P03_WildFarm.Animals;



import Polymorphism.Exercise.P03_WildFarm.Food.Food;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;

    public Mammal(String animalName, String animalType, Integer foodEaten, Double animalWeight, String livingRegion) {
        super(animalName, animalType, foodEaten, animalWeight);
        this.livingRegion = livingRegion;
    }


    public String getLivingRegion() {
        return livingRegion;
    }

    public void setLivingRegion(String livingRegion) {
        this.livingRegion = livingRegion;
    }
    public  void  eat(Food food){
        super.setFoodEaten(super.getFoodEaten());
    }
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        return String.format("%s[%s, %s, %s, %d]",getAnimalType(), getAnimalName(), decimalFormat.format(getAnimalWeight()), getLivingRegion(), getFoodEaten());
    }
}
