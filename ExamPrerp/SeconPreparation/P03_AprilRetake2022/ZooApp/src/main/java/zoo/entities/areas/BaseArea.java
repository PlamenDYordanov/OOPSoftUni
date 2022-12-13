package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area {
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        return foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.capacity == this.animals.size()) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
    animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%s (%s):", getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        if (getAnimals().isEmpty()) {
            output.append("Animals: none");
        }else {
            output.append(String.format("Animals: %s", this.animals.stream().map(Animal::getName)
                    .collect(Collectors.joining(" "))));
        }
        output.append(System.lineSeparator());
        output.append(String.format("Foods: %d",getFoods().size()));
        output.append(System.lineSeparator());
        output.append(String.format("Calories: %d", this.foods.stream().mapToInt(Food::getCalories).sum()));
        return output.toString().trim();
    }
}
