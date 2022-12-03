package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.horsePower = horsePower;
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if (model == null || model.trim().length() < 4) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL,model, 4));
        }
        this.model = model;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    protected void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
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
        return (cubicCentimeters / horsePower) * laps;
    }
}
