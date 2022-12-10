package christmasPastryShop.core;

import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.OpenBooth;
import christmasPastryShop.entities.booths.PrivateBooth;
import christmasPastryShop.entities.cocktails.Hibernation;
import christmasPastryShop.entities.cocktails.MulledWine;
import christmasPastryShop.entities.delicacies.Gingerbread;
import christmasPastryShop.entities.delicacies.Stolen;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.BoothRepositoryImpl;
import christmasPastryShop.repositories.CocktailRepositoryImpl;
import christmasPastryShop.repositories.DelicacyRepositoryImpl;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import static christmasPastryShop.common.ExceptionMessages.BOOTH_EXIST;
import static christmasPastryShop.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalSum;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
        this.totalSum = 0;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        if (delicacyRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        Delicacy delicacy = null;
        switch (type) {
            case "Gingerbread":
                delicacy = new Gingerbread(name, price);
                break;
            case "Stolen":
                delicacy = new Stolen(name, price);
                break;
        }

        delicacyRepository.add(delicacy);
        return String.format(DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        if (cocktailRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        Cocktail cocktail = null;
        switch (type) {
            case "Hibernation":
                cocktail = new Hibernation(name, size, brand);
                break;
            case "MulledWine":
                cocktail = new MulledWine(name, size, brand);
                break;
        }


        cocktailRepository.add(cocktail);
        return String.format(COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        if (boothRepository.getByNumber(boothNumber) != null) {
            throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
        }
        Booth booth = null;
        switch (type) {
            case "OpenBooth":
                booth = new OpenBooth(boothNumber, capacity);
                break;
            case "PrivateBooth":
                booth = new PrivateBooth(boothNumber, capacity);
                break;
        }


        boothRepository.add(booth);
        return String.format(BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth currentBooth = boothRepository.getAll().stream()
                .filter(booth -> !booth.isReserved() && booth.getCapacity() >= numberOfPeople).
                findFirst()
                .orElse(null);
        if (currentBooth == null) {
            throw new IllegalArgumentException(String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople));
        }
        currentBooth.reserve(numberOfPeople);

        return String.format(BOOTH_RESERVED, currentBooth.getBoothNumber(), numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth currentBooth = boothRepository.getByNumber(boothNumber);
        double bill = 0;
        bill = currentBooth.getBill();
        totalSum += bill;
        currentBooth.clear();

        return String.format(BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(TOTAL_INCOME, totalSum);
    }
}
