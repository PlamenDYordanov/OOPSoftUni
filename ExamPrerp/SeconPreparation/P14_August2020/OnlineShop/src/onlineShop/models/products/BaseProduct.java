package onlineShop.models.products;

import onlineShop.common.constants.ExceptionMessages;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseProduct implements Product{
    private int id;
    private String manufacturer;
    private String model;
    private double price;
    private double overallPerformance;

    protected BaseProduct(int id, String manufacturer, String model, double price, double overallPerformance) {
        this.setId(id);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setPrice(price);
        this.setOverallPerformance(overallPerformance);
    }

    @Override
    public int getId() {
        return this.id;
    }

    private void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRODUCT_ID);
        } else {
            this.id = id;
        }
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    private void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MANUFACTURER);
        } else {
            this.manufacturer = manufacturer;
        }
    }

    @Override
    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MODEL);
        } else {
            this.model = model;
        }
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        } else {
            this.price = price;
        }
    }

    @Override
    public double getOverallPerformance() {
        return this.overallPerformance;
    }

    public void setOverallPerformance(double overallPerformance) {
        if (overallPerformance <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_OVERALL_PERFORMANCE);
        } else {
            this.overallPerformance = overallPerformance;
        }
    }
    @Override
    public String toString(){
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: " +
                        "%s %s (Id: %d)",
                getOverallPerformance(), getPrice(), this.getClass().getSimpleName(),
                getManufacturer(), getModel(), getId());
    }
}
