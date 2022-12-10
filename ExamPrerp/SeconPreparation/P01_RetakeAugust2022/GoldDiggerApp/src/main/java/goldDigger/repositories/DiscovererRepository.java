package goldDigger.repositories;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.museum.Museum;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiscovererRepository implements Repository<Discoverer> {
    private Map<String, Discoverer> discoverers;

    public DiscovererRepository() {
        this.discoverers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(this.discoverers.values());
    }

    @Override
    public void add(Discoverer entity) {
        this.discoverers.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return this.discoverers.remove(entity.getName(), entity);
    }

    @Override
    public Discoverer byName(String name) {
        return this.discoverers.get(name);
    }
}
