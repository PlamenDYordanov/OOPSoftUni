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
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }
        Driver newDriver = new DriverImpl(driverName);
        driverRepository.add(newDriver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = null;
        StringBuilder output = new StringBuilder();
        switch (type) {
            case "MuscleCar":
                car = new MuscleCar(model, horsePower);
                output.append("MuscleCar");
                break;
            case "SportsCar":
                car = new SportsCar(model, horsePower);
                output.append("SportsCar");
                break;

        }
        if (carRepository.getByName(model) == null) {
            carRepository.add(car);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }
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
        raceRepository.getByName(raceName).addDriver(driverRepository.getByName(driverName));
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        Race currentRace = raceRepository.getByName(raceName);
        List<Driver> getFirst3Drivers =
                currentRace.getDrivers().stream()
                        .limit(3)
                        .sorted((left, right) -> {
                            int result = Double.compare(right.getCar().calculateRacePoints(currentRace.getLaps()), left.getCar().calculateRacePoints(currentRace.getLaps()));
                            return result;
                        }).collect(Collectors.toList());
        if (getFirst3Drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }
        StringBuilder output = new StringBuilder();
        Driver firstDriver = getFirst3Drivers.get(0);
        output.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, firstDriver.getName(), currentRace.getName()));
        Driver secondDriver = getFirst3Drivers.get(1);
        output.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, secondDriver.getName(), currentRace.getName()));
        Driver thirdDriver = getFirst3Drivers.get(2);
        output.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, thirdDriver.getName(), currentRace.getName()));
        return null;
    }

    @Override
    public String createRace(String name, int laps) {
        if (raceRepository.getByName(name) == null) {
            Race race = new RaceImpl(name, laps);
            raceRepository.add(race);
        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
