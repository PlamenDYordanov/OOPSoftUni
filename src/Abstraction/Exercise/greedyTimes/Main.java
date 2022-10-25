
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


        Map<String, Map<String, Long>> bagOfItems = new LinkedHashMap<>();
        long gold = 0;
        long gem = 0;
        long money = 0;

        for (int i = 0; i < items.length; i += 2) {
            String name = items[i];
            long countOfItem = Long.parseLong(items[i + 1]);

            String currentItem = "";

            if (name.length() == 3) {
                currentItem = "Cash";
            } else if (name.toLowerCase().endsWith("gem")) {
                currentItem = "Gem";
            } else if (name.toLowerCase().equals("gold")) {
                currentItem = "Gold";
            }

            if (currentItem.equals("")) {
                continue;
            } else if (capacityOfBag < bagOfItems.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + countOfItem) {
                continue;
            }

            switch (currentItem) {
                case "Gem":
                    if (checkItem(bagOfItems, currentItem, "Gold", countOfItem)) continue;
                    break;
                case "Cash":
                    if (checkItem(bagOfItems, currentItem, "Gem", countOfItem)) continue;
                    break;
            }

            if (!bagOfItems.containsKey(currentItem)) {
                bagOfItems.put((currentItem), new LinkedHashMap<String, Long>());
            }

            if (!bagOfItems.get(currentItem).containsKey(name)) {
                bagOfItems.get(currentItem).put(name, 0L);
            }
            bagOfItems.get(currentItem).put(name, bagOfItems.get(currentItem).get(name) + countOfItem);
            switch (currentItem) {
                case "Gold":
                     gold += countOfItem;
                     break;
                case "Gem":
                     gem += countOfItem;
                     break;
                case "Cash":
                    money += countOfItem;
                    break;
            }
        }

        for (Map.Entry<String, Map<String, Long>> entry : bagOfItems.entrySet()) {
            Long sumValues = entry.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.printf("<%s> $%s%n", entry.getKey(), sumValues);

            entry.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }

    private static boolean checkItem(Map<String, Map<String, Long>> bagOfItems, String currentItem, String item, long countOfItem) {
        if (!bagOfItems.containsKey(currentItem)) {
            if (bagOfItems.containsKey(item)) {
                if (countOfItem > bagOfItems.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                    return true;
                }
            } else {
                return true;
            }
        } else if (bagOfItems.get(currentItem).values().stream().mapToLong(e -> e).sum() + countOfItem > bagOfItems.get(item).values().stream().mapToLong(e -> e).sum()) {
            return true;
        }
        return false;
    }
}