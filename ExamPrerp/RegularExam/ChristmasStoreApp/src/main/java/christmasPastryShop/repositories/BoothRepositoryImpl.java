package christmasPastryShop.repositories;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class BoothRepositoryImpl implements Repository<Booth>, BoothRepository<Booth> {
    Collection<Booth> models;

    public BoothRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Booth> getAll() {
        return this.models;
    }

    @Override
    public void add(Booth booth) {
        this.models.add(booth);
    }

    @Override
    public Booth getByNumber(int number) {
        return this.models.stream().filter(booth -> booth.getBoothNumber() == number).findFirst().orElse(null);
    }
}
