package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;


public class SportsCar extends BaseCar{
    private static final  double DEFAULT_CUBIC_CENTIMETERS  = 3000;
    private static  final  double DEFAULT_MIN_HORSEPOWER = 250;
    private static  final  double DEFAULT_MAX_HORSEPOWER = 450;
    public SportsCar(String model, int horsePower, double cubicCentimeters) {
        super(model, horsePower, cubicCentimeters);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < DEFAULT_MIN_HORSEPOWER || horsePower > DEFAULT_MAX_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,getHorsePower()));
        }
        super.setHorsePower(horsePower);

    }
}
