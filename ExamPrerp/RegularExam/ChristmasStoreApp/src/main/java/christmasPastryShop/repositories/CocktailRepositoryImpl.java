package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl extends BaseRepository<Cocktail> implements CocktailRepository<Cocktail> {


    @Override
    public Cocktail getByName(String name) {
        return super.getAll().stream().filter(booth -> booth.getName().equals(name)).findFirst().orElse(null);
    }

}
