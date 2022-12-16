package easterRaces.entities.racers;

import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;

import java.util.ArrayList;
import java.util.Collection;

import static easterRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race{
    private  String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 5) {
            throw new IllegalArgumentException(String.format(INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS,1));
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
        if (driver == null) {
            throw new IllegalArgumentException(DRIVER_INVALID);
        }
        if (!driver.getCanParticipate()) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE, driver.getName()));
        }
        Driver currDriver = this.drivers.stream().filter(driver1 -> driver1.getName().equals(driver.getName())).findFirst().orElse(null);
        if (currDriver != null) {
            throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED,currDriver.getName(), getName()));
        }
        this.drivers.add(driver);

    }
}
