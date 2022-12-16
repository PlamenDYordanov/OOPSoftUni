package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{
    private static final double CUBIC_CENTIMETERS = 3000;
    private static final int MINIMUM_HORSE_POWER = 250;
    private static final int MAXIMUM_HORSE_POWER = 450;
    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
        checkHorsePower(horsePower);
    }

    @Override
    public void checkHorsePower(int horsePower) {
        if (horsePower < MINIMUM_HORSE_POWER || horsePower > MAXIMUM_HORSE_POWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
