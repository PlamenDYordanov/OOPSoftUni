package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    HelperRepository helperRepository;
    PresentRepository presentRepository;
    public ControllerImpl() {
        helperRepository = new HelperRepository();
        presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type){
            case "Happy":
                helper = new Happy(helperName);
                helperRepository.add(helper);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                helperRepository.add(helper);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
       if (helperRepository.findByName(helperName) == null) {
           throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
       }
       Instrument currentInstrument = new InstrumentImpl(power);
        Helper currentHelper = helperRepository.findByName(helperName);
        currentHelper.addInstrument(currentInstrument);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present currentPresent = new PresentImpl(presentName, energyRequired);
        presentRepository.add(currentPresent);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Shop shop = new ShopImpl();
        Present requirePresent = presentRepository.findByName(presentName);
        List<Helper> helpersAbove50Energy = helperRepository.getModels().stream()
                .filter(helper -> helper.getEnergy() > 50)
                .collect(Collectors.toList());
        if (helpersAbove50Energy.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }
        for (Helper helper : helpersAbove50Energy) {
            shop.craft(requirePresent, helper);
            if (requirePresent.isDone()){   //!!!!!!!!!!!
                break;
            }
        }
        int countOfBrokenInstruments = 0;
        for (Helper helpers : helpersAbove50Energy) {
            for (Instrument instrument : helpers.getInstruments()) {
               if (instrument.isBroken()) {
                   countOfBrokenInstruments++;
               }
            }
        }
        StringBuilder output = new StringBuilder();
        if (requirePresent.isDone()) {
            output.append(String.format(ConstantMessages.PRESENT_DONE, requirePresent.getName(), "done"));
        }else {
            output.append(String.format(ConstantMessages.PRESENT_DONE, requirePresent.getName(), "not done"));
        }
        output.append(String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, countOfBrokenInstruments));

        return output.toString();
    }

    @Override
    public String report() {
        int totalCraftedPresent = 0;
        for (Present model : presentRepository.getModels()) {
           if (model.isDone()){
                totalCraftedPresent++;
            }
        }
        StringBuilder output = new StringBuilder();
        output.append(String.format("%d presents are done!",totalCraftedPresent));
        output.append(System.lineSeparator());
        output.append("Helpers info:");
        output.append(System.lineSeparator());
        for (Helper model : helperRepository.getModels()) {
            output.append(String.format("Name: %s", model.getName()));
            output.append(System.lineSeparator());
            output.append(String.format("Energy: %d", model.getEnergy()));
            output.append(System.lineSeparator());
            int countOfDurableInstruments = 0;
            for (Instrument instrument : model.getInstruments()) {
                if (!instrument.isBroken()) {
                    countOfDurableInstruments++;
                }
            }
            output.append(String.format("Instruments: %d not broken left",countOfDurableInstruments));
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
