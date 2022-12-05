package restaurant.entities.tables;

public class Indoors extends BaseTable{
    private static final double DEFAULT_PRICE = 3.50;
    public Indoors(int number, int size) {
        super(number, size, DEFAULT_PRICE);
    }
}
