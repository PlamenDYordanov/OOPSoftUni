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

import java.util.ArrayList;
import java.util.Collection;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
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
        this.houses.add(house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy = null;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        if (toys.findFirst(toyType) == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }
        Toy curToy = toys.findFirst(toyType);
        House curHouse = this.houses.stream().filter(house -> house.getName().equals(houseName)).findFirst().get();
        curHouse.buyToy(curToy);
        this.toys.removeToy(curToy);
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        House curHouse = houses.stream().filter(house -> house.getName().equals(houseName)).findFirst().get();
        if (catType.equals("ShorthairCat") && curHouse.getClass().getSimpleName().equals("ShortHouse")) {
            curHouse.addCat(cat);
        } else if (catType.equals("LonghairCat") && curHouse.getClass().getSimpleName().equals("LongHouse")) {
            curHouse.addCat(cat);
        } else {
            return UNSUITABLE_HOUSE;
        }
        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House curHouse = houses.stream().filter(house -> house.getName().equals(houseName)).findFirst().get();
        curHouse.feeding();
        return String.format(FEEDING_CAT, curHouse.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House curHouse = houses.stream().filter(house -> house.getName().equals(houseName)).findFirst().get();
        double toysPrice = curHouse.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double catsPrice = curHouse.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double totalPrice = toysPrice + catsPrice;
        return String.format(VALUE_HOUSE, houseName, totalPrice);
    }

    @Override
    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        for (House house : houses) {
            output.append(house.getStatistics()).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
