package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CarRepository implements Repository<Car> {
    private Collection<Car> Models;

    public CarRepository() {
        this.Models = new ArrayList<>();
    }

    @Override
    public Car getByName(String name) {
        return this.Models.stream()
                .filter(car -> car.getModel().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.Models);
    }

    @Override
    public void add(Car model) {
        this.Models.add(model);
    }

    @Override
    public boolean remove(Car model) {
        return this.Models.remove(model);
    }
}
