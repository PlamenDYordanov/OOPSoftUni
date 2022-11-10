package P01_Vehicles;

import java.text.DecimalFormat;

public abstract class BaseVehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    public BaseVehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }
    public  void driving(double distance){
        if (getFuelQuantity() >= distance * getFuelConsumption()) {
            setFuelQuantity(getFuelQuantity() - distance * getFuelConsumption());
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            System.out.println(String.format("%s travelled %s km", getClass().getSimpleName(),decimalFormat.format(distance)));
        }else {
            System.out.println(String.format("%s needs refueling", getClass().getSimpleName()));
        }
    }

    public abstract void refueling(double refuelQuantity);

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f" ,getClass().getSimpleName(), getFuelQuantity());
    }
}
