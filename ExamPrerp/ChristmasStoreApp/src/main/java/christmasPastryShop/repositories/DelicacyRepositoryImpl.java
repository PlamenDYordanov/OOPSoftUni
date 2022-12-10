package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

public class DelicacyRepositoryImpl extends BaseRepository<Delicacy> implements DelicacyRepository<Delicacy> {
    @Override
    public Delicacy getByName(String name) {
        return super.getAll().stream()
                .filter(d->d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
