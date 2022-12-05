package restaurant.entities.tables;

public class InGarden extends BaseTable{
    private static final double DEFAULT_PRICE = 4.50;
    public InGarden(int number, int size) {
        super(number, size, DEFAULT_PRICE);
    }
}
