package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{
    private static final double DEFAULT_CUBIC_CENTIMETERS = 5000;
    private static final int MINIMUM_HORSEPOWER = 400;
    private static final int MAXIMUM_HORSEPOWER = 600;
    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
        checkHorsePower(horsePower);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < MINIMUM_HORSEPOWER || horsePower > MAXIMUM_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
