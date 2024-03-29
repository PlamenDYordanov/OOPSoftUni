package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;
import christmasPastryShop.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DelicacyRepositoryImpl extends BaseRepository<Delicacy> implements DelicacyRepository<Delicacy> {


    @Override
    public Delicacy getByName(String name) {
        return super.getAll().stream().filter(booth -> booth.getName().equals(name)).findFirst().orElse(null);
    }


}
