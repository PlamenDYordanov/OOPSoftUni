package P01_Vehicles;

import java.text.DecimalFormat;

public class BaseVehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    private double tankCapacity;

    public BaseVehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        setFuelQuantity(fuelQuantity);
        setFuelConsumption(fuelConsumption);
        setTankCapacity(tankCapacity);

    }

    public void driving(double distance) {
        if (getFuelQuantity() >= distance * getFuelConsumption()) {
            setFuelQuantity(getFuelQuantity() - distance * getFuelConsumption());
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            System.out.println(String.format("%s travelled %s km", getClass().getSimpleName(), decimalFormat.format(distance)));
        } else {
            System.out.println(String.format("%s needs refueling", getClass().getSimpleName()));
        }
    }

    public void refueling(double refuelQuantity) {
        if (getTankCapacity() < refuelQuantity + getFuelQuantity()) {
            System.out.println("Cannot fit fuel in tank");
        } else if (refuelQuantity <= 0) {
            System.out.println("Fuel must be a positive number");
        } else {
            setFuelQuantity(getFuelQuantity() + refuelQuantity);
        }
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity > 0) {
            this.fuelQuantity = fuelQuantity;
        } else {
            System.out.println("Fuel must be positive number");
        }
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getClass().getSimpleName(), getFuelQuantity());
    }
}
