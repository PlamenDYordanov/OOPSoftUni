package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        if (driverRepository.getByName(driverName) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }
        Driver newDriver = new DriverImpl(driverName);
        driverRepository.add(newDriver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        for (Car car : carRepository.getAll()) {
            if (car.getModel().equals(model)) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
            }
        }
        Car car = null;
        StringBuilder output = new StringBuilder();
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                output.append("MuscleCar");
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                output.append("SportsCar");
                break;

        }
        carRepository.add(car);
        return String.format(OutputMessages.CAR_CREATED, output, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (carRepository.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        driverRepository.getByName(driverName).addCar(carRepository.getByName(carModel));
        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        Driver currentDriver = driverRepository.getByName(driverName);
        raceRepository.getByName(raceName).addDriver(currentDriver);
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        Race currentRace = raceRepository.getByName(raceName);
        if (currentRace.getDrivers() == null || currentRace.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }
        List<Driver> getFirst3Drivers =
                currentRace.getDrivers().stream()
                        .limit(3)
                        .sorted((left, right) -> Double.compare(right.getCar().calculateRacePoints(currentRace.getLaps()), left.getCar().calculateRacePoints(currentRace.getLaps()))).collect(Collectors.toList());
        StringBuilder output = new StringBuilder();
        Driver firstDriver = getFirst3Drivers.get(0);
        output.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, firstDriver.getName(), currentRace.getName()));
        output.append(System.lineSeparator());
        Driver secondDriver = getFirst3Drivers.get(1);
        output.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, secondDriver.getName(), currentRace.getName()));
        output.append(System.lineSeparator());
        Driver thirdDriver = getFirst3Drivers.get(2);
        output.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, thirdDriver.getName(), currentRace.getName()));
        output.append(System.lineSeparator());
        return output.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        if (raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
