package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
   private HelperRepository helperRepository;
   private PresentRepository presentRepository;
   private int totalCraftPresent;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.totalCraftPresent = 0;
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            case "Happy":
                helper = new Happy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(helper);

        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        if (helperRepository.findByName(helperName) == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Instrument currentInstrument = new InstrumentImpl(power);
        helperRepository.findByName(helperName).addInstrument(currentInstrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER,power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present newPresent = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(newPresent);
        return String.format(SUCCESSFULLY_ADDED_PRESENT,presentName);
    }

    @Override
    public String craftPresent(String presentName) {
       Helper helper =
                helperRepository.getModels().stream()
                        .filter(helper1 -> helper1.getEnergy() > 50)
                        .findFirst()
                        .orElse(null);
        if (helper == null) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        Present present = presentRepository.findByName(presentName);
        Shop shop = new ShopImpl();
        shop.craft(present, helper);
        long brokenInstrument = helper.getInstruments().stream()
                .filter(Instrument::isBroken).count();
        StringBuilder output = new StringBuilder();
        if (!present.isDone()) {
           output.append(String.format(PRESENT_DONE,presentName,"not done"));
        }else {
            totalCraftPresent++;
            output.append(String.format(PRESENT_DONE,presentName,"done"));
        }
        output.append(String.format(COUNT_BROKEN_INSTRUMENTS,brokenInstrument));
        return output.toString();
    }

    @Override
    public String report() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%d presents are done!", totalCraftPresent)).append(System.lineSeparator());
        output.append("Helpers info:").append(System.lineSeparator());
        for (Helper helper : helperRepository.getModels()) {
            output.append(String.format("Name: %s", helper.getName())).append(System.lineSeparator());
            output.append(String.format("Energy: %d", helper.getEnergy()));
            output.append(System.lineSeparator());
            long notBrokenInstrument = helper.getInstruments().stream().filter(instrument -> !instrument.isBroken()).count();
            output.append(String.format("Instruments: %d not broken left", notBrokenInstrument));
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
