package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpotRepository implements Repository<Spot>{
   private Map<String, Spot> collection;

    public SpotRepository() {
        this.collection = new LinkedHashMap<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(collection.values());
    }

    @Override
    public void add(Spot spot) {
        collection.put(spot.getName(), spot);
    }

    @Override
    public boolean remove(Spot entity) {
        return collection.remove(entity.getName()) != null;
    }

    @Override
    public Spot byName(String name) {

        return collection.get(name);
    }
}
