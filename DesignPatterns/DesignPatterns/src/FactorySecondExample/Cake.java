package FactorySecondExample;

public class Cake implements CakeInterface {

    private double diameter;
    private double price;
    private int pieces;
    private String type;

    public Cake(double diameter, double price, int pieces) {
        this.diameter = diameter;
        this.price = price;
        this.pieces = pieces;
    }

    @Override
    public void prepare() {
    }

    @Override
    public void bake() {

    }

    @Override
    public void box() {

    }
}
