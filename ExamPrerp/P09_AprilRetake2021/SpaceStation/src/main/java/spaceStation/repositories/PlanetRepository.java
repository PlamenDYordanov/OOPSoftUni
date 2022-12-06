package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlanetRepository implements Repository<Planet>{
    private Map<String, Planet> planets;

    public PlanetRepository() {
        this.planets = new LinkedHashMap<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return this.planets.values();
    }

    @Override
    public void add(Planet name) {
        planets.putIfAbsent(name.getName(), name);
    }

    @Override
    public boolean remove(Planet model) {
        return planets.remove(model.getName(), model);
    }

    @Override
    public Planet findByName(String name) {
        return this.planets.get(name);
    }
}
