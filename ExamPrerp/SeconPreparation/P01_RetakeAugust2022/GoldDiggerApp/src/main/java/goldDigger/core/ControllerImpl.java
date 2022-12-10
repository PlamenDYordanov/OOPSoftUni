package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import javax.print.DocFlavor;
import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;
    private int countOfInspectedSpots;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        switch (kind) {
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(String.format(DISCOVERER_INVALID_KIND));
        }
        discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED,kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
        this.spotRepository.add(spot);
        return String.format(SPOT_ADDED,spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null) {
           throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST,discovererName));
       }
       discovererRepository.remove(discoverer);
        return String.format(DISCOVERER_EXCLUDE,discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> currentDiscoverer =
        this.discovererRepository
                .getCollection()
                .stream()
                .filter(discoverer -> discoverer.getEnergy() > 45)
                .collect(Collectors.toList());
        if (currentDiscoverer.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Spot spot = this.spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, currentDiscoverer);
        long excludedDiscoverer = currentDiscoverer.stream().filter(discoverer -> !discoverer.canDig()).count();
        countOfInspectedSpots ++;
        return String.format(INSPECT_SPOT, spotName, excludedDiscoverer );
    }

    @Override
    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        output.append(String.format(FINAL_SPOT_INSPECT, countOfInspectedSpots)).append(System.lineSeparator());
        output.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        for (Discoverer discoverer : discovererRepository.getCollection()) {
            output.append(String.format(FINAL_DISCOVERER_NAME,discoverer.getName())).append(System.lineSeparator());
            output.append(String.format(FINAL_DISCOVERER_ENERGY,discoverer.getEnergy())).append(System.lineSeparator());
            if (discoverer.getMuseum().getExhibits().isEmpty()){
                output.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS,"None"));
            }else {
                output.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, String.join(", ", discoverer.getMuseum().getExhibits())));
            }
            output.append(System.lineSeparator());
        }

        return output.toString().trim();
    }
}
