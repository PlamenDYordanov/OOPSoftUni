package christmasPastryShop.repositories;

import christmasPastryShop.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;

public abstract class BaseRepository<T> implements Repository<T> {

    private Collection<T> models;
    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(T t) {
        models.add(t);
    }
}
