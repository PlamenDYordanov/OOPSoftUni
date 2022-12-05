package restaurant.entities.drinks;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;

public abstract class BaseBeverage implements Beverages {

    private String name;
    private int counter;
    private double price;
    private String brand;


    public BaseBeverage(String name, int counter, String brand, double price) {
        this.setName(name);
        this.setCounter(counter);
        this.setBrand(brand);
        this.setPrice(price);

    }


    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    public void setCounter(int counter) {
        if (counter <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COUNTER);
        }
        this.counter = counter;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    public void setBrand(String brand) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.brand = brand;
    }
}
