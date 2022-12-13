package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State currentState = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            currentState.getExhibits().add(exhibit);
        }
        stateRepository.add(currentState);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        if (explorerRepository.byName(explorerName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorerRepository.byName(explorerName));
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> explorerAbove50Energy =
                explorerRepository.getCollection().stream().filter(explorer -> explorer.getEnergy() > 50)
                        .collect(Collectors.toList());
        if (explorerAbove50Energy.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        Mission mission = new MissionImpl();
        mission.explore(stateRepository.byName(stateName), explorerAbove50Energy);
        long countOfRetired = explorerAbove50Energy.stream().filter(explorer -> explorer.getEnergy() == 0).count();
        this.exploredStates++;
        return String.format(ConstantMessages.STATE_EXPLORER, stateName, countOfRetired);
    }

    @Override
    public String finalResult() {
        StringBuilder output = new StringBuilder();

        output.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, exploredStates));
        output.append(System.lineSeparator());
        output.append(String.format(ConstantMessages.FINAL_EXPLORER_INFO));
        output.append(System.lineSeparator());

        for (Explorer explorer : explorerRepository.getCollection()) {
            output.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, explorer.getName()));
            output.append(System.lineSeparator());
            output.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, explorer.getEnergy()));
            output.append(System.lineSeparator());
            if (explorer.getSuitcase().getExhibits().isEmpty()) {
                output.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS, "None"));
            } else {
                output.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,
                        String.join(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, explorer.getSuitcase().getExhibits())));
            }
            output.append(System.lineSeparator());

        }
        return output.toString().trim();
    }
}

