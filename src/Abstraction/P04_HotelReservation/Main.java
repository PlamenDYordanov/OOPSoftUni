package Abstraction.P04_HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] data = scanner.nextLine().split("\\s+");

        double price = Double.parseDouble(data[0]);
        int days = Integer.parseInt(data[1]);
        String season = data[2];
        String discount = data[3];

        Season currentSeason = Season.valueOf(season);
        int currentMultiplier = currentSeason.getMultiplier();
        DiscountType currentDiscount = DiscountType.valueOf(discount);
        double currentDiscountPercent = currentDiscount.getDiscount();
        double finalPrice = PriceCalculator.total(price, days, currentSeason, currentDiscount);

        System.out.printf("%.2f",finalPrice);

    }
}
