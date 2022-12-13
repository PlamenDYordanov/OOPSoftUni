package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;
import christmasPastryShop.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DelicacyRepositoryImpl extends BaseRepository<Delicacy> implements Repository<Delicacy>, DelicacyRepository<Delicacy> {
   private Collection<Delicacy> models;

    public DelicacyRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Delicacy getByName(String name) {
        return this.models.stream().filter(booth -> booth.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Delicacy> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Delicacy delicacy) {
        this.models.add(delicacy);
    }
}
