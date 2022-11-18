package barracksWars.core.commands;

import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public abstract class Command implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory factory;

    public Command(String[] data) {
        this.data = data;

    }

    public String[] getData() {
        return data;
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getFactory() {
        return factory;
    }
}
