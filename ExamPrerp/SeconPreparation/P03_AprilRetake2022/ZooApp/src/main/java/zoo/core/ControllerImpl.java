package zoo.core;

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

import static zoo.common.ConstantMessages.*;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;
        switch (areaType) {
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
            default:
                throw new NullPointerException(INVALID_AREA_TYPE);
        }
        this.areas.add(area);
        return String.format(String.format(SUCCESSFULLY_ADDED_AREA_TYPE,areaType));
    }

    @Override
    public String buyFood(String foodType) {
       Food food;
       switch (foodType) {
           case "Vegetable":
               food = new Vegetable();
               break;
           case "Meat":
               food = new Meat();
               break;
           default:
               throw new IllegalArgumentException(INVALID_FOOD_TYPE);
       }
       foodRepository.add(food);
        return String.format(String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType));
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food requireFood = foodRepository.findByType(foodType);
        if (requireFood == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND,foodType));
        }
       Area requireArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);
        requireArea.addFood(requireFood);
        foodRepository.remove(requireFood);
        return String.format(String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName));
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;
        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, animalType, price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }
        Area requireArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);
        if (animalType.equals("AquaticAnimal") && requireArea.getClass().getSimpleName().equals("WaterArea")) {
            requireArea.addAnimal(animal);
        }else if (animalType.equals("TerrestrialAnimal") && requireArea.getClass().getSimpleName().equals("LandArea")) {
            requireArea.addAnimal(animal);
        }else {
            return  AREA_NOT_SUITABLE;
        }

        return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area requireArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);
        requireArea.feed();
        return String.format(ANIMALS_FED,requireArea.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area requireArea = areas.stream().filter(area -> area.getName().equals(areaName)).findFirst().orElse(null);
       double totalKg = requireArea.getAnimals().stream().mapToDouble(Animal::getKg).sum();
        return String.format(KILOGRAMS_AREA,areaName,totalKg);
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
