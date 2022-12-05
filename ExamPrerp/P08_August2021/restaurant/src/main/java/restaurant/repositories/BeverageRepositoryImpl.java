package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.ArrayList;
import java.util.Collection;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private Collection<Beverages> entities;

    public BeverageRepositoryImpl() {
        this.entities = new ArrayList<>();
    }
    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return entities.stream().filter(beverage -> beverage.getName().equals(drinkName) && beverage.getBrand().equals(drinkBrand)).findFirst().orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return this.entities;
    }

    @Override
    public void add(Beverages entity) {
        this.entities.add(entity);
    }
}
