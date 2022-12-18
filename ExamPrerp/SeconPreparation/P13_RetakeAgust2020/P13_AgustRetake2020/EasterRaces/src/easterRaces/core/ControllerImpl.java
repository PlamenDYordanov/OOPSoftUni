package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.CarRepository;
import easterRaces.repositories.DriverRepository;
import easterRaces.repositories.RaceRepository;
import easterRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> carRepository;
    private Repository<Driver> driverRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Car> carRepository, Repository<Driver> driverRepository, Repository<Race> raceRepository) {
        this.carRepository = new CarRepository();
        this.driverRepository = new DriverRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String name) {
        if (driverRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, name));
        }
        Driver newDriver = new DriverImpl(name);
        this.driverRepository.add(newDriver);
        return String.format(DRIVER_CREATED, name);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        Car car = null;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
        }

        carRepository.add(car);

        return String.format(CAR_CREATED, type + "Car", model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        if (carRepository.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        driverRepository.getByName(driverName).addCar(carRepository.getByName(carModel));
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
        raceRepository.getByName(raceName).addDriver(driverRepository.getByName(driverName));
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Race currentRace = raceRepository.getByName(raceName);
        int countOfDrivers = raceRepository.getByName(raceName).getDrivers().size();
        int laps = currentRace.getLaps();
        if (countOfDrivers < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        List<Driver> topThreeDrivers = currentRace.getDrivers().stream()
                .limit(3)
                .sorted((left, right) -> Double.compare(right.getCar().calculateRacePoints(laps),
                        left.getCar().calculateRacePoints(laps)))
                .collect(Collectors.toList());
        return String.format(DRIVER_FIRST_POSITION, topThreeDrivers.get(0).getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_SECOND_POSITION, topThreeDrivers.get(1).getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_THIRD_POSITION, topThreeDrivers.get(2).getName(), raceName) + System.lineSeparator().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        Race newRace = new RaceImpl(name, laps);
        this.raceRepository.add(newRace);
        return String.format(RACE_CREATED, name);
    }
}
