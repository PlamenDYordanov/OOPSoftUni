package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if (model == null || model.trim().length() < 4) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model));
        }
        this.model = model;
    }

    protected abstract void checkHorsePower(int horsePower);

    protected void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }


    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return cubicCentimeters / (horsePower * laps);
    }
}
