package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorations;
    private Map<String, Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new LinkedHashMap<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        switch (aquariumType) {
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        aquariums.put(aquariumName, aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        decorations.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        if (decorations.findByType(decorationType) == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }
        Decoration decoration = decorations.findByType(decorationType);
        Aquarium aquarium = aquariums.get(aquariumName);
        aquarium.addDecoration(decoration);
        decorations.remove(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }
        Aquarium aquarium = aquariums.get(aquariumName);
        String typeOfAquarium = aquarium.getClass().getSimpleName();
        StringBuilder output = new StringBuilder();
        if (fish.getClass().getSimpleName().equals("FreshwaterFish") && typeOfAquarium.equals("FreshwaterAquarium")) {
            output.append(String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName));
            aquarium.addFish(fish);
        } else if (fish.getClass().getSimpleName().equals("SaltwaterFish") && typeOfAquarium.equals("SaltwaterAquarium")) {
            output.append(String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName));
            aquarium.addFish(fish);
        } else {
            return WATER_NOT_SUITABLE;
        }


        return output.toString();
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = aquariums.get(aquariumName);
        aquarium.getFish().forEach(Fish::eat);
        return String.format(FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        double totalSum = 0;
        Aquarium currentAquarium = aquariums.get(aquariumName);
        double sumDecoration = currentAquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        double sumFish = currentAquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        totalSum = sumDecoration + sumFish;

        return String.format(VALUE_AQUARIUM, aquariumName, totalSum);
    }

    @Override
    public String report() {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Aquarium> entry : aquariums.entrySet()) {
            output.append(entry.getValue().getInfo()).append(System.lineSeparator());
        }

        return output.toString().trim();
    }
}
