package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends  BaseCar{
    private static final double DEFAULT_CUBIC_CENTIMETERS = 3000;
    private static final int MINIMUM_HP = 250;
    private static final int MAXIMUM_HP = 450;
    public SportsCar(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if (horsePower < MINIMUM_HP || horsePower > MAXIMUM_HP) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, getHorsePower()));
        }
        super.setHorsePower(horsePower);
    }
}
