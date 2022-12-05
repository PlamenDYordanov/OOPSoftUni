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
    private int numberOfPeople;
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

    public void setReservedTable(boolean reservedTable) {
        isReservedTable = reservedTable;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
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
        return getNumberOFPeople() * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        setNumberOFPeople(numberOfPeople);
        this.isReservedTable = true;
    }

    public void setNumberOFPeople(int numberOFPeople) {
        this.numberOfPeople += numberOFPeople;
    }

    public int getNumberOFPeople() {
        return numberOfPeople;
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
        double currentBill = 0;

        for (HealthyFood food : healthyFood) {
            currentBill += food.getPrice();
        }

        for (Beverages currentBeverage : beverages) {
            currentBill += currentBeverage.getPrice();
        }

        return currentBill + allPeople();
    }

    @Override
    public void clear() {
        beverages.clear();
        healthyFood.clear();
        this.numberOfPeople = 0;
        this.allPeople = 0;
        this.isReservedTable = false;
    }

    @Override
    public String tableInformation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Table - %d", getTableNumber())).append(System.lineSeparator());
        stringBuilder.append(String.format("Size - %d", getSize())).append(System.lineSeparator());
        stringBuilder.append(String.format("Type - %s", getClass().getSimpleName())).append(System.lineSeparator());
        stringBuilder.append(String.format("All price - %.2f", pricePerPerson())).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
