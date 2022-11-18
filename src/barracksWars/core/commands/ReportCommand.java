package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class ReportCommand extends Command {
    Repository repository;
    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.getRepository().getStatistics();
    }
}
