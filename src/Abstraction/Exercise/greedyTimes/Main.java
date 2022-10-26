
package Abstraction.Exercise.greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long capacityOfBag = Long.parseLong(scanner.nextLine());
        String[] items = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(capacityOfBag);

        for (int i = 0; i < items.length; i += 2) {
            String name = items[i];
            long countOfItem = Long.parseLong(items[i + 1]);


            if (name.length() == 3) {
                bag.addCash(name, countOfItem);
            } else if (name.toLowerCase().endsWith("gem")) {
                bag.addGem(name, countOfItem);
            } else if (name.toLowerCase().equals("gold")) {
                bag.addGold(name, countOfItem);
            }
        }
        bag.printContent();

    }
}
