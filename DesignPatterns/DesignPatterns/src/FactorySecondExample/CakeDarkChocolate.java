package FactorySecondExample;

public class CakeDarkChocolate extends Cake implements  CakeInterface{
    public CakeDarkChocolate(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }
}
