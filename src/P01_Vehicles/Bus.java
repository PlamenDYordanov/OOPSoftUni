package P01_Vehicles;

import java.text.DecimalFormat;

public class Bus extends BaseVehicle {

    private static final double ADDITIONAL_AC_CONSUMPTION = 1.4;


    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }


    @Override
    public void driving(double distance) {
        if (isEmpty()) {
            if (this.getFuelQuantity() >= distance * this.getFuelConsumption()) {
                this.setFuelQuantity(this.getFuelQuantity() - distance * this.getFuelConsumption());
                DecimalFormat decimalFormat = new DecimalFormat("##.##");
                System.out.println(String.format("%s travelled %s km", getClass().getSimpleName(), decimalFormat.format(distance)));
            } else {
                System.out.println(String.format("%s needs refueling", getClass().getSimpleName()));
            }
        } else {
            if (this.getFuelQuantity() >= distance * (this.getFuelConsumption() + ADDITIONAL_AC_CONSUMPTION)) {
                this.setFuelQuantity(this.getFuelQuantity() - distance * (this.getFuelConsumption() + ADDITIONAL_AC_CONSUMPTION));
                DecimalFormat decimalFormat = new DecimalFormat("##.##");
                System.out.println(String.format("%s travelled %s km", getClass().getSimpleName(), decimalFormat.format(distance)));
            } else {
                System.out.println(String.format("%s needs refueling", getClass().getSimpleName()));
            }
        }
    }
}
