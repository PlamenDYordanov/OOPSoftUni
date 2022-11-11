package Polymorphism.Exercise.P03_WildFarm.Animals;


import Polymorphism.Exercise.P03_WildFarm.Food.Food;

public abstract class Animal {
    private String animalName;
    private String animalType;
    private Double animalWeight;
    private Integer foodEaten = 0;

    public Animal(String animalName, String animalType,Integer foodEaten, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten = foodEaten;

    }

    public abstract void makeSound();
    public abstract void eat(Food food);


    public void setFoodEaten(Integer foodEaten) {
        this.foodEaten = foodEaten;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public Double getAnimalWeight() {
        return animalWeight;
    }

    public Integer getFoodEaten() {
        return foodEaten;
    }


}
