package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;
import christmasPastryShop.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class DelicacyRepositoryImpl implements Repository<Delicacy>, DelicacyRepository<Delicacy> {
    Collection<Delicacy> models;

    public DelicacyRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Delicacy getByName(String name) {
        return this.models.stream().filter(booth -> booth.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Delicacy> getAll() {
        return this.models;
    }

    @Override
    public void add(Delicacy delicacy) {
        this.models.add(delicacy);
    }
}
