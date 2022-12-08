package onlineShop.models.products.components;

import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct  implements  Component{
    private int generation;
    public BaseComponent(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    protected abstract void multiplyOverallPerformance(double performance);


    @Override
    public int getGeneration() {
        return this.generation;
    }

    @Override
    public String toString() {
        String output = String.format(" Generation: %d", this.generation);
        return super.toString() + output;
    }
}
