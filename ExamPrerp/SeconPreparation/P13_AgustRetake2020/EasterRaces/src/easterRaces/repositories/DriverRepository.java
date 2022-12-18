package easterRaces.repositories;

import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DriverRepository implements Repository<Driver> {
    private Collection<Driver> Models;

    public DriverRepository() {
        this.Models = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.Models
                .stream()
                .filter(driver -> driver.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.Models);
    }

    @Override
    public void add(Driver model) {
        this.Models.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        return this.Models.remove(model);
    }
}
