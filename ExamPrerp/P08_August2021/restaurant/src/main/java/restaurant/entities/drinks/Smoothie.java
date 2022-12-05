package restaurant.entities.drinks;

public class Smoothie extends BaseBeverage {
    public Smoothie(String name, int counter, String brand, double price) {
        super(name, counter, brand, price);
    }

    @Override
    public String getName() {
        return ;
    }

    @Override
    public int getCounter() {
        return 0;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getBrand() {
        return null;
    }
}
