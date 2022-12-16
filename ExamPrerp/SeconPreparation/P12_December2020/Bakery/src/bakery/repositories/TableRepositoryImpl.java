package bakery.repositories;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

public class TableRepositoryImpl extends BaseRepository<Table> implements TableRepository<Table> {
    @Override
    public Table getByNumber(int number) {
        return super.getAll().stream().filter(table -> table.getTableNumber() == number).findFirst().orElse(null);
    }
}
