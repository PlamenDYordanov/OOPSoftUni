package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.*;

public class HelperRepository<T> implements Repository<Helper> {
    private Map<String , Helper> helpers;

    public HelperRepository() {
        this.helpers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableCollection(this.helpers.values());
    }

    @Override
    public void add(Helper model) {
        helpers.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Helper model) {
        return helpers.remove(model.getName()) != null;
    }

    @Override
    public Helper findByName(String name) {
        return helpers.get(name);
    }
}
