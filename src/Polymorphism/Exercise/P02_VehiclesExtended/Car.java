package Polymorphism.Exercise.P02_VehiclesExtended;

public class Car extends BaseVehicle {
    private static final double ADDITIONAL_AC_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_AC_CONSUMPTION, tankCapacity);
    }


    }

