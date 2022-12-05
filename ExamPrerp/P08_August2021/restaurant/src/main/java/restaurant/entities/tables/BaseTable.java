package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOFPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.isReservedTable = false;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    private void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOFPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.numberOFPeople * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
    this.isReservedTable = true;//!!!
    this.numberOFPeople += numberOfPeople;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
       this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
       this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double priceBeverages = beverages.stream().mapToDouble(Beverages::getPrice).sum();
        double priceFood = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        return (priceBeverages + priceFood) + allPeople;
    }

    @Override
    public void clear() {
        beverages.clear();
        healthyFood.clear();
    }

    @Override
    public String tableInformation() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Table - %d",this.number));
        output.append(System.lineSeparator());
        output.append(String.format("Size - %d",this.size));
        output.append(System.lineSeparator());
        output.append(String.format("Type - %s", this.getClass().getSimpleName()));
        output.append(System.lineSeparator());
        output.append(String.format("All price - %.2f", this.pricePerPerson));
        output.append(System.lineSeparator());
        return output.toString();
    }
}
