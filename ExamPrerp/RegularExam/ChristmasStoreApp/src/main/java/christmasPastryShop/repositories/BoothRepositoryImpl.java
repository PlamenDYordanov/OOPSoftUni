package christmasPastryShop.repositories;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;


import java.util.Collection;


public class BoothRepositoryImpl extends BaseRepository<Booth> implements BoothRepository<Booth> {

    @Override
    public Booth getByNumber(int number) {
        return super.getAll().stream().filter(booth -> booth.getBoothNumber() == number).findFirst().orElse(null);
    }
}
