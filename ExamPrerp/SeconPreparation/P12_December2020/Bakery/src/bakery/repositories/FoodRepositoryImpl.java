package bakery.repositories;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.tables.BaseTable;
import bakery.repositories.interfaces.FoodRepository;

public class FoodRepositoryImpl extends BaseRepository<BakedFood> implements FoodRepository<BakedFood> {
    @Override
    public BakedFood getByName(String name) {
        return super.getAll().stream().filter(bakedFood -> bakedFood.getName().equals(name)).findFirst().orElse(null);
    }
}
