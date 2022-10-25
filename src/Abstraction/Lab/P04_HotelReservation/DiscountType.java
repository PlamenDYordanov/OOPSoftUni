package Abstraction.Lab.P04_HotelReservation;

public enum DiscountType {
    VIP(0.80),
    SecondVisit(0.90),
    None(1);
    ;

    private double discount;

    DiscountType(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
