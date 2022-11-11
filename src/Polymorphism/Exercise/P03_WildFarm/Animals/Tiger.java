package Polymorphism.Exercise.P03_WildFarm.Animals;


import Polymorphism.Exercise.P03_WildFarm.Food.Food;
import Polymorphism.Exercise.P03_WildFarm.Food.Vegetable;

public class Tiger extends Felime {


    public Tiger(String animalName, String animalType, Integer foodEaten, Double animalWeight, String livingRegion) {
        super(animalName, animalType, foodEaten, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Vegetable){
            System.out.println("Tigers are not eating that type of food!");
            super.setFoodEaten(0);
        }else {
            super.eat(food);
        }
    }

}
