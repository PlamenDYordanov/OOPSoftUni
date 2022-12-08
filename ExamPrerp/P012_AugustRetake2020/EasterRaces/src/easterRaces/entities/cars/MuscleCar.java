package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{
    private static final  double DEFAULT_CUBIC_CENTIMETERS  = 5000;
    private static  final  double DEFAULT_MIN_HORSEPOWER = 400;
    private static  final  double DEFAULT_MAX_HORSEPOWER = 600;
    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CENTIMETERS);
        checkHorsePower(horsePower);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < DEFAULT_MIN_HORSEPOWER || horsePower > DEFAULT_MAX_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,getHorsePower()));
        }
        super.setHorsePower(horsePower);
    }
}
