package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Map<String, House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        houses.put(name, house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(String.format(INVALID_TOY_TYPE));
        }
        toys.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }
        houses.get(houseName).buyToy(toy);
        toys.removeToy(toy);
        return String.format(String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName));
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        String currentHouse = houses.get(houseName).getClass().getSimpleName();
        if (cat.getClass().getSimpleName().equals("LonghairCat") && currentHouse.equals("LongHouse")) {
            houses.get(houseName).addCat(cat);
        } else if (cat.getClass().getSimpleName().equals("ShorthairCat") && currentHouse.equals("ShortHouse")) {
            houses.get(houseName).addCat(cat);
        } else {
            return UNSUITABLE_HOUSE;
        }
        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, cat.getClass().getSimpleName(), houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        houses.get(houseName).feeding();
        return String.format(FEEDING_CAT, houses.get(houseName).getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        double catTotalSum = houses.get(houseName).getCats().stream().mapToDouble(Cat::getPrice).sum();
        double toyTotalSum = houses.get(houseName).getToys().stream().mapToDouble(Toy::getPrice).sum();
        return String.format(VALUE_HOUSE, houseName, catTotalSum + toyTotalSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        for (House currentHouse : houses.values()) {
           output.append(currentHouse.getStatistics());
           output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
