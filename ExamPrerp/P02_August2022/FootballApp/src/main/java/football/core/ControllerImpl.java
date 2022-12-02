package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.BaseField;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.FIELD_NOT_SUITABLE;
import static football.common.ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.fields = new ArrayList<>();
        this.supplementRepository = new SupplementRepositoryImpl();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field = null;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                fields.add(field);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                fields.add(field);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        if (type.equals("Powdered") || type.equals("Liquid")) {
            createSupplement(type);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

    }

    private void createSupplement(String type) {
        Supplement supplement;
        switch (type) {
            case "Powdered":
                supplement = new Powdered();
                supplementRepository.add(supplement);
                break;
            case "Liquid":
                supplement = new Liquid();
                supplementRepository.add(supplement);
                break;
        }
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {

        if (supplementRepository.findByType(supplementType) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        } else {
            Supplement currentSupplement = supplementRepository.findByType(supplementType);
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    field.addSupplement(currentSupplement);
                    supplementRepository.remove(currentSupplement);
                    break;
                }
            }
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;
        Field field = getCurrentField(fieldName);
    //    String typeOfTerrain = field.getClass().getSimpleName();   with that 122/150 !!!!!!!!!!!!!
        switch (playerType) {
            case "Men":
                    player = new Men(playerName, nationality, strength);
                break;
            case "Women":
                    player = new Women(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        if (field.getClass().getSimpleName().equals("ArtificialTurf") && playerType.equals("Women")) {
            field.addPlayer(player);
        } else if (field.getClass().getSimpleName().equals("NaturalGrass") && playerType.equals("Men")) {
            field.addPlayer(player);;
        } else {
            return FIELD_NOT_SUITABLE;
        }
        return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    private Field getCurrentField(String fieldName) {
        return fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = getCurrentField(fieldName);
        for (Player player : field.getPlayers()) {
            player.stimulation();
        }
        int sizeOfDragPlayer = field.getPlayers().size();
        return String.format(ConstantMessages.PLAYER_DRAG, sizeOfDragPlayer);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = getCurrentField(fieldName);
        int sumOfStrength = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, sumOfStrength);
    }

    @Override
    public String getStatistics() {
        StringBuilder output = new StringBuilder();
        for (Field field : fields) {
            output.append(field.getInfo());
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
