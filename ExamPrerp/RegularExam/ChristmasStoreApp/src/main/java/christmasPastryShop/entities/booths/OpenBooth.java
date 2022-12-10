package christmasPastryShop.entities.booths;

public class OpenBooth extends BaseBooth{
    private static final double DEFAULT_PRICE_PER_PERSON = 2.50;
    public OpenBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, DEFAULT_PRICE_PER_PERSON);
    }

    @Override
    protected void setCapacity(int capacity) {
        super.setCapacity(capacity);
    }
}
