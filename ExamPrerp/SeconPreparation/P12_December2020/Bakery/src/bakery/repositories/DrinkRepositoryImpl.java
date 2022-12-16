package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

public class DrinkRepositoryImpl extends BaseRepository<Drink> implements DrinkRepository<Drink> {
    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return super.getAll().stream().filter(drink -> drink.getName().equals(drinkName) && drink.getBrand().equals(drinkBrand)).findFirst().orElse(null);
    }
}
