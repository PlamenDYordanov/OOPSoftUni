package easterRaces.repositories;

import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverRepository implements Repository<Driver> {

    private Map<String, Driver> Models;

    public DriverRepository() {
        this.Models = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return Models.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.Models.values());
    }

    @Override
    public void add(Driver model) {
        Models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Driver model) {
        return Models.remove(model.getName()) != null;
    }
}
