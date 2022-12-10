package christmasPastryShop.entities.booths;

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

    protected void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getBoothNumber() {
        return this.boothNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.numberOfPeople * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReserved = true;
        setNumberOfPeople(numberOfPeople);
        double priceForBooth = getPrice();

    }

    @Override
    public double getBill() {
        double sumCocktails = delicacyOrders.stream().mapToDouble(x -> x.getPrice() * numberOfPeople).sum();
        double sumDelicacy = delicacyOrders.stream().mapToDouble(x -> x.getPrice() * numberOfPeople).sum();
        double totalPricePerPerson = getPrice();
        return sumDelicacy + sumCocktails + totalPricePerPerson;
    }

    @Override
    public void clear() {
        this.cocktailOrders.clear();
        this.delicacyOrders.clear();
        this.isReserved = false;
        this.numberOfPeople = 0;
        this.price = 0;
    }
}
