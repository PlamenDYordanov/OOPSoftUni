package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

public class BeverageRepositoryImpl extends BaseRepository<Beverages> implements BeverageRepository<Beverages> {
    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return super.getAllEntities().stream()
                .filter(beverages -> beverages.getName().equals(drinkName) && beverages.getBrand().equals(drinkBrand))
                .findFirst()
                .orElse(null);
    }
}
