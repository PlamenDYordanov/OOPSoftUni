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
    AstronautRepository astronautRepository;
    PlanetRepository planetRepository;
    private int totalExplorePlanet;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.totalExplorePlanet = 0;
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
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet newPlanet = new PlanetImpl(planetName);
        for (String item : items) {
            newPlanet.getItems().add(item);
        }
        planetRepository.add(newPlanet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronautNameCurrent = astronautRepository.findByName(astronautName);
        if (astronautNameCurrent == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        astronautRepository.remove(astronautNameCurrent);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet currentExplorePlanet = planetRepository.findByName(planetName);
        List<Astronaut> above60Energy =
                astronautRepository.getModels().stream().filter(astronaut -> astronaut.getOxygen() > 60).collect(Collectors.toList());
        if (above60Energy.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Mission newMission = new MissionImpl();
        newMission.explore(currentExplorePlanet, above60Energy);
        long deadAstronauts = above60Energy.stream().filter(astronaut -> astronaut.getOxygen() == 0).count();
        totalExplorePlanet++;

        return String.format(PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_PLANET_EXPLORED, totalExplorePlanet)).append(System.lineSeparator());
        sb.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        for (Astronaut astronaut : astronautRepository.getModels()) {
            sb.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName())).append(System.lineSeparator());
            sb.append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen())).append(System.lineSeparator());
            if (astronaut.getBag().getItems().isEmpty()) {
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none"));
            } else {
                sb.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, String.join(", ", astronaut.getBag().getItems())));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
