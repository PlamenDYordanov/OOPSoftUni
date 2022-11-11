package Polymorphism.Exercise.P03_WildFarm.Animals;


import Polymorphism.Exercise.P03_WildFarm.Food.Food;
import Polymorphism.Exercise.P03_WildFarm.Food.Meat;

public class Zebra extends Mammal {


    public Zebra(String animalName, String animalType, Integer foodEaten, Double animalWeight, String livingRegion) {
        super(animalName, animalType, foodEaten, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Meat) {
            System.out.println("Zebras are not eating that type of food!");
            super.setFoodEaten(0);
        }else {
            super.eat(food);
        }
    }
}
