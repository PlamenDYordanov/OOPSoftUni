package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements  Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        drivers = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name ,5));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
             throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS,1));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver.getName() == null) {
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
        }else if (driver.getCar() == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,driver.getName()));
        }

            for (Driver driver1 : drivers) {
                if (driver1.getName().equals(driver.getName())) {
                    throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,driver.getName(), name));
                }
            }

        this.drivers.add(driver);
    }
}
