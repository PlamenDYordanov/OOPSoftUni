package restaurant.entities.healthyFoods;

import restaurant.common.ExceptionMessages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;

public abstract class Food implements HealthyFood {
    private String name;
    private double portion;
    private double price;

    public Food(String name, double price, double portion) {
        this.setName(name);
        this.setPrice(price);
        this.setPortion(portion);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    private void setPortion(double portion) {
        if (ifIsZeroThrow(portion)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PORTION);
        }
        this.portion = portion;
    }

    private void setPrice(double price) {
        if (ifIsZeroThrow(price)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
    private boolean ifIsZeroThrow(double number) {
        return number <= 0;
    }
}
