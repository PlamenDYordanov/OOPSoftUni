package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AstronautRepository implements Repository<Astronaut> {
    private Map<String, Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new LinkedHashMap<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return this.astronauts.values();
    }

    @Override
    public void add(Astronaut model) {
        astronauts.putIfAbsent(model.getName(),model);
    }

    @Override
    public boolean remove(Astronaut model) {
        return astronauts.remove(model.getName(),model);
    }

    @Override
    public Astronaut findByName(String name) {
        return astronauts.get(name);
    }
}
