package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        foodRepository = new FoodRepositoryImpl();
        areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;
        if (areaType.equals("WaterArea")) {
            area = new WaterArea(areaName);
            areas.add(area);
        } else if (areaType.equals("LandArea")) {
            area = new LandArea(areaName);
            areas.add(area);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        if (foodType.equals("Meat")) {
            food = new Meat();
            foodRepository.add(food);
        } else if (foodType.equals("Vegetable")) {
            food = new Vegetable();
            foodRepository.add(food);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        if (foodRepository.findByType(foodType) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        }
        Food currentFood = foodRepository.findByType(foodType);
        Area currentArea = getCurrentArea(areaName);
        currentArea.addFood(currentFood);
        return String.format(String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName));
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;
        Area currentArena = getCurrentArea(areaName);
        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }
        if (animalType.equals("AquaticAnimal") && currentArena.getClass().getSimpleName().equals("WaterArea")) {
            currentArena.addAnimal(animal);
        } else if (animalType.equals("TerrestrialAnimal") && currentArena.getClass().getSimpleName().equals("LandArea")) {
            currentArena.addAnimal(animal);
        } else {
            return String.format(ConstantMessages.AREA_NOT_SUITABLE);
        }
        return String.format(String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName));
    }

    @Override
    public String feedAnimal(String areaName) {
        Area currentArea = getCurrentArea(areaName);
        currentArea.feed();
        return String.format(ConstantMessages.ANIMALS_FED, currentArea.getAnimals().size());
    }

    private Area getCurrentArea(String areaName) {
        return areas.stream().filter(area -> area.getName().equals(areaName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String calculateKg(String areaName) {
        Area currentArea = getCurrentArea(areaName);
        double totalCalories = currentArea.getAnimals().stream().mapToDouble(Animal::getKg).sum();
        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, totalCalories);
    }

    @Override
    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        for (Area area : areas) {
            output.append(area.getInfo());
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
