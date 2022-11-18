package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class RetireCommand extends Command{
    Repository repository;
    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        try{
            String unitType = getData()[1];
            getRepository().removeUnit(getData()[1]);
            return String.format("%s retired!", unitType);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
}
