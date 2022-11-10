package P01_Vehicles;

public class Truck extends  BaseVehicle{

    private static final double ADDITIONAL_AC_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_AC_CONSUMPTION);
    }



    @Override
    public void refueling(double refuelQuantity) {
        setFuelQuantity(getFuelQuantity() + (refuelQuantity * 0.95));
    }
}
