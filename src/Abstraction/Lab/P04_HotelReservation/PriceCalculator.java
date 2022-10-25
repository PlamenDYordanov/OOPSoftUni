package Abstraction.Lab.P04_HotelReservation;

public class PriceCalculator {

    public static double total(double price, int days, Season season, DiscountType discount) {
        return (days * (price * season.getMultiplier())) * discount.getDiscount();
    }

}
