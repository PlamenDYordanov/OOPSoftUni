package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private  double totalIncome;


    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
    }


    @Override
    public String addFood(String type, String name, double price) {
        BakedFood bakedFood = this.foodRepository.getByName(name);
        if (bakedFood != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        switch (type) {
            case "Bread":
                bakedFood = new Bread(name, price);
                break;
            case "Cake":
                bakedFood = new Cake(name, price);
                break;
        }
        this.foodRepository.add(bakedFood);
        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink drink = drinkRepository.getByNameAndBrand(name, brand);
        if (drink != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        switch (type) {
            case "Tea":
                drink = new Tea(name, portion, brand);
                break;
            case "Water":
                drink = new Water(name, portion, brand);
                break;
        }
        this.drinkRepository.add(drink);
        //TODO:
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (table != null) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }
        switch (type) {
            case "InsideTable":
                table = new InsideTable(tableNumber, capacity);
                break;
            case "OutsideTable":
                table = new OutsideTable(tableNumber, capacity);
                break;
        }
        this.tableRepository.add(table);
        //TODO:
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Table requireTable = this.tableRepository
                .getAll().stream()
                .filter(table -> !table.isReserved() && table.getCapacity() >= numberOfPeople)
                .findFirst()
                .orElse(null);
        if (requireTable == null) {
            return  String.format(String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople));
        }
        requireTable.reserve(numberOfPeople);
        //TODO:
        return String.format(TABLE_RESERVED, requireTable.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table requireTable = this.tableRepository.getByNumber(tableNumber);
        if (requireTable == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        BakedFood bakedFood = this.foodRepository.getByName(foodName);
        if (bakedFood == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }
        requireTable.orderFood(bakedFood);
        //TODO:
        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table requireTable = this.tableRepository.getByNumber(tableNumber);
        if (requireTable == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        Drink drink = this.drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }
        requireTable.orderDrink(drink);
        //TODO:
        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);

    }

    @Override
    public String leaveTable(int tableNumber) {
        Table requireTable = this.tableRepository.getByNumber(tableNumber);
        double bill = requireTable.getBill();
        totalIncome += bill;
        requireTable.clear();
        //TODO:
        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder sb = new StringBuilder();
        for (Table table : this.tableRepository.getAll()) {
            if(!table.isReserved()){
                sb.append(table.getFreeTableInfo());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        //TODO:
        return String.format(TOTAL_INCOME, totalIncome);
    }
}
