package Abstraction.P04_HotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private int days;
    private double discount;
    private double multiplier;

    public PriceCalculator(double pricePerDay, int days) {
        this.pricePerDay = pricePerDay;
        this.days = days;
    }

    public static double total(double price, int days, Season season, DiscountType discount) {
        return (days * (price * season.getMultiplier())) * discount.getDiscount();
    }

}
