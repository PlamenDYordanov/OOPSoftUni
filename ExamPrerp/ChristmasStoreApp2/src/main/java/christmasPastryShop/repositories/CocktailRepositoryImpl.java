package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.repositories.interfaces.CocktailRepository;

public class CocktailRepositoryImpl extends BaseRepository<Cocktail> implements CocktailRepository<Cocktail> {
    @Override
    public Cocktail getByName(String name) {
        return super.getAll().stream()
                .filter(c->c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
