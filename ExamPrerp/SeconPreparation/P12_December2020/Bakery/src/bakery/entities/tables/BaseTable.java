package bakery.entities.tables;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import java.util.Collection;

import static bakery.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static bakery.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseTable implements Table {
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.tableNumber = tableNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        this.isReserved = false;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        setReserved(true);
        setNumberOfPeople(numberOfPeople);
        setPrice(getPricePerPerson() * getNumberOfPeople());

    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
    this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double sumDrinks = this.drinkOrders.stream().mapToDouble(Drink::getPrice).sum();
        double sumFoods = this.foodOrders.stream().mapToDouble(BakedFood::getPrice).sum();
        return sumDrinks + sumFoods + getPrice();
    }

    @Override
    public void clear() {
        setReserved(false);
        setNumberOfPeople(0);
        setPrice(0);
        this.drinkOrders.clear();
        this.foodOrders.clear();
    }

    @Override
    public String getFreeTableInfo() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Table: %d",getTableNumber())).append(System.lineSeparator());
        output.append(String.format("Type: %s",this.getClass().getSimpleName())).append(System.lineSeparator());
        output.append(String.format("Capacity: %d",getCapacity())).append(System.lineSeparator());
        output.append(String.format("Price per Person: %.2f",getPricePerPerson()));
        return output.toString();
    }
}
