package fairyShop.models;

import fairyShop.common.ExceptionMessages;

public class PresentImpl implements Present{
    private String name;
    private int energyRequire;
    private static final int DEFAULT_DECREASE_ENERGY_REQUIRE = 10;

    public PresentImpl(String name, int energyRequire) {
        this.setName(name);
        this.setEnergyRequire(energyRequire);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergyRequire(int energyRequire) {
        if (energyRequire < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequire = energyRequire;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequire;
    }

    @Override
    public boolean isDone() {
        return this.energyRequire == 0;
    }

    @Override
    public void getCrafted() {
        this.energyRequire = Math.max(0, this.energyRequire - DEFAULT_DECREASE_ENERGY_REQUIRE);
    }
}
