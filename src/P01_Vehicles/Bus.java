package P01_Vehicles;

public class Bus extends BaseVehicle{

    private static final double ADDITIONAL_AC_CONSUMPTION = 1.4;
    private boolean isEmpty =  true;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        this.isEmpty = empty;
    }
}
