package christmasPastryShop.entities.booths;

public class PrivateBooth extends BaseBooth{
    private static final double DEFAULT_PRICE_PER_PERSON = 3.50;
    public PrivateBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, DEFAULT_PRICE_PER_PERSON);
    }

    @Override
    protected void setCapacity(int capacity) {
        super.setCapacity(capacity);
    }
}
