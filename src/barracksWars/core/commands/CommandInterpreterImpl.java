package barracksWars.core.commands;

import barracksWars.interfaces.CommandInterpreter;
import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    public final static String COMMAND_PACKAGE_NAME = "barracksWars.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String className = parseCommandToClassName(commandName);
        Executable command = null;
        Class clazz = Class.forName(className);
        Constructor<Command> constructor = clazz.getDeclaredConstructor(String[].class, Repository.class, UnitFactory.class);
        command = constructor.newInstance(data, repository,unitFactory);
        return command;
        /*switch (commandName) {
            case "add":
                return new AddUnitCommand(data, repository, unitFactory);
            case "report":
                return new ReportCommand(data, repository, unitFactory);
            case "fight":
                return new FightCommand(data, repository, unitFactory);
            case "retire":
                return new RetireCommand(data, repository, unitFactory);
            default:
                throw new RuntimeException("Invalid command!");
        }
*/
    }

    private String parseCommandToClassName(String commandName) {
        String firstLetterUpperCase = commandName.substring(0, 1).toUpperCase();
        String restOfTheCommand = commandName.substring(1);
        return COMMAND_PACKAGE_NAME + firstLetterUpperCase + restOfTheCommand + "Command";
    }
}

