package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class CocktailRepositoryImpl implements Repository<Cocktail>, CocktailRepository<Cocktail> {

    Collection<Cocktail> models;

    public CocktailRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        return this.models.stream().filter(booth -> booth.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return this.models;
    }

    @Override
    public void add(Cocktail cocktail) {
        this.models.add(cocktail);
    }
}
