package Polymorphism.Exercise.P02_VehiclesExtended;

public class Truck extends BaseVehicle {

    private static final double ADDITIONAL_AC_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_AC_CONSUMPTION, tankCapacity);
    }


    @Override
    public void refueling(double refuelQuantity) {
        refuelQuantity = refuelQuantity * 0.95;
        super.refueling(refuelQuantity);
    }
}

