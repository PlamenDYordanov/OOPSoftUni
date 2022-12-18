package easterRaces.repositories;

import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> Models;

    public RaceRepository() {
        this.Models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return this.Models
                .stream()
                .filter(car -> car.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.Models);
    }

    @Override
    public void add(Race model) {
        this.Models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return this.Models.remove(model);
    }
}
