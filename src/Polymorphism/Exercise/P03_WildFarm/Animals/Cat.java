package Polymorphism.Exercise.P03_WildFarm.Animals;



import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalName, String animalType, Integer foodEaten, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, foodEaten, animalWeight, livingRegion);
        this.breed = breed;
    }


    @Override
    public void makeSound() {
        System.out.println("Meowwww");

    }


    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        return String.format("%s[%s, %s, %s, %s, %d]",getAnimalType(), getAnimalName(), getBreed(), decimalFormat.format(getAnimalWeight()), getLivingRegion(), getFoodEaten());
    }
}

