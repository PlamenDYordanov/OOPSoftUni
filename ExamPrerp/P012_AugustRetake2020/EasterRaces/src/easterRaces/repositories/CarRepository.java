package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarRepository implements Repository<Car> {

   private Map<String, Car> Models;

    public CarRepository() {
        this.Models = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return Models.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.Models.values());
    }

    @Override
    public void add(Car model) {
        Models.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        return Models.remove(model.getModel()) != null;
    }
}
