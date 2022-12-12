package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> carRepository;
    private Repository<Driver> driverRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String name) {
        if (driverRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, name));
        }
        Driver currentDriver = new DriverImpl(name);
        driverRepository.add(currentDriver);
        return String.format(DRIVER_CREATED, name);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        Car car = null;
        switch (type) {
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
        }
        carRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        if (carRepository.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        Car addedCar = carRepository.getByName(carModel);
        this.driverRepository.getByName(driverName).addCar(addedCar);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Driver addDriver = driverRepository.getByName(driverName);
        raceRepository.getByName(raceName).addDriver(addDriver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        List<Driver> participateDrivers =
                this.raceRepository.getByName(raceName)
                        .getDrivers()
                        .stream()
                        .filter(Driver::getCanParticipate)
                        .collect(Collectors.toList());
        Race race = raceRepository.getByName(raceName);
        if (participateDrivers.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        List<Driver> sortedDriver = participateDrivers
                .stream()
                .sorted(((left, right) -> Double.compare(right.getCar().calculateRacePoints(race.getLaps()), left.getCar().calculateRacePoints(race.getLaps()))))
                .collect(Collectors.toList());
        String output = String.format(DRIVER_FIRST_POSITION, sortedDriver.get(0).getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_SECOND_POSITION, sortedDriver.get(1).getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_THIRD_POSITION, sortedDriver.get(2).getName(), raceName) + System.lineSeparator();
        return output.trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race createdRace = raceRepository.getByName(name);
        if (createdRace != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        createdRace = new RaceImpl(name, laps);
        raceRepository.add(createdRace);
        return String.format(RACE_CREATED, name);
    }
}
