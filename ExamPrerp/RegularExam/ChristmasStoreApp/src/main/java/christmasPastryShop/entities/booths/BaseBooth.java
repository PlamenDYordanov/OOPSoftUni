package christmasPastryShop.entities.booths;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.common.enums.CocktailType;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static christmasPastryShop.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static christmasPastryShop.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseBooth implements Booth {
    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.boothNumber = boothNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        this.isReserved = false;
        this.cocktailOrders = new ArrayList<>();
        this.delicacyOrders = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }


    @Override
    public int getBoothNumber() {
        return this.boothNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double getPrice() {
        return getNumberOfPeople()  * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
        setPrice(pricePerPerson * numberOfPeople);
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double getBill() {
        double currentBill = 0;
        for (Cocktail cocktailOrder : cocktailOrders) {
            currentBill += cocktailOrder.getPrice();
        }
        for (Delicacy delicacyOrder : delicacyOrders) {
            currentBill += delicacyOrder.getPrice();
        }

        return currentBill + getPrice();
    }

    @Override
    public void clear() {
        this.cocktailOrders.clear();
        this.delicacyOrders.clear();
        this.isReserved = false;
        setNumberOfPeople(0);
        setPrice(0);

    }
}
