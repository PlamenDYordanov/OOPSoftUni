package barracksWars.core.commands;

import barracksWars.Anotations.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class RetireCommand extends Command{
        @Inject
    private Repository repository;
    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        try{
            String unitType = getData()[1];
            repository.removeUnit(getData()[1]);
            return String.format("%s retired!", unitType);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
}
