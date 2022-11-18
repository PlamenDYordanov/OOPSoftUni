package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;

public class AddCommand extends Command{

    Repository repository;
    UnitFactory unitFactory;
    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];;
        Unit unitToAdd = getFactory().createUnit(unitType);
        getRepository().addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
