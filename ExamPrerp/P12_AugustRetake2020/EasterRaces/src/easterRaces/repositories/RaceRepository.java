package easterRaces.repositories;

import easterRaces.entities.drivers.Driver;
import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceRepository implements Repository<Race> {
    private Map<String, Race> Models;

    public RaceRepository() {
        Models = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return Models.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.Models.values());
    }

    @Override
    public void add(Race model) {
        this.Models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        return this.Models.remove(model.getName()) != null;
    }
}
