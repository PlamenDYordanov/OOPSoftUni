package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar {
    private static final double CUBIC_CENTIMETERS_DEFAULT = 5000;
    private static final  double DEFAULT_HP_MIN = 400;
    private static final  double DEFAULT_HP_MAX = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS_DEFAULT);
        this.checkHorsePower(horsePower);
    }

    private void checkHorsePower(int horsePower) {
        if (horsePower < DEFAULT_HP_MIN || horsePower > DEFAULT_HP_MAX) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
    }
}
