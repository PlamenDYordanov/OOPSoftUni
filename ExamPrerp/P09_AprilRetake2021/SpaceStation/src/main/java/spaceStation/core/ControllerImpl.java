package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int countOfExplorePlanet;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.countOfExplorePlanet = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw  new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED,type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items) {
            planet.getItems().add(item);
        }
        planetRepository.add(planet);
        return String.format(PLANET_ADDED,planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
      if (astronautRepository.findByName(astronautName) == null) {
          throw  new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST,astronautName));
      }
      Astronaut currentAstronaut =  astronautRepository.findByName(astronautName);
      astronautRepository.remove(currentAstronaut);
        return String.format(ASTRONAUT_RETIRED,astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet currentPlanet = planetRepository.findByName(planetName);
        List<Astronaut> suitableAstronaut = astronautRepository.getModels()
                .stream()
                .filter(astronaut -> astronaut.getOxygen() > 60).collect(Collectors.toList());
        if (suitableAstronaut.isEmpty()) {
            throw  new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Mission newMission = new MissionImpl();
        newMission.explore(currentPlanet, suitableAstronaut);
        countOfExplorePlanet++;
        long deadAstronaut = suitableAstronaut.stream()
                .filter(astronaut -> astronaut.getOxygen() == 0) .count();
        return String.format(PLANET_EXPLORED, planetName, deadAstronaut);
    }

    @Override
    public String report() {
        StringBuilder output = new StringBuilder();
        output.append(String.format(REPORT_PLANET_EXPLORED, countOfExplorePlanet)).append(System.lineSeparator());
        output.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        for (Astronaut astronaut : astronautRepository.getModels()) {
            output.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName())).append(System.lineSeparator());
            output.append(String.format(REPORT_ASTRONAUT_OXYGEN,astronaut.getOxygen())).append(System.lineSeparator());
            if (astronaut.getBag().getItems().isEmpty()){
                output.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "None"));
            }else {
              output.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, astronaut.getBag().getItems())));
            }
            output.append(System.lineSeparator());
        }

        return output.toString();
    }
}
