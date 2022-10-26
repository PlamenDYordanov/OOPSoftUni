package Abstraction.Exercise.greedyTimes;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bag {
    private long capacity;


    private Map<String, Map<String, Long>> item;
    private long currentCapacity;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.item = new LinkedHashMap<>();

    }

    public void addCash(String name, long countOfItem) {
        if (countOfItem + currentAmountOfItem(this.item, "Cash") <= capacity && countOfItem + currentAmountOfItem(this.item, "Cash") <= currentAmountOfItem(this.item, "Gem")) {
            if (!this.item.containsKey("Cash")) {
                this.item.put("Cash", new HashMap<>());
                this.item.get("Cash").put(name, countOfItem);
            } else {
                this.item.get("Cash").put(name, countOfItem + currentAmountOfItem(this.item, name));
            }
            currentCapacity += countOfItem;
        }
    }

    public void addGold(String name, long countOfItem) {
        if (countOfItem + currentAmountOfItem(this.item, name) <= capacity) {
            if (!this.item.containsKey("Gold")) {
                this.item.put(name, new HashMap<>());
                this.item.get(name).put(name, countOfItem);
            } else {
                this.item.get(name).put("Gold", countOfItem + currentAmountOfItem(this.item, name));
            }
            currentCapacity += countOfItem;
        }
    }

    public void addGem(String name, long countOfItem) {
        if (countOfItem + currentAmountOfItem(this.item, "Gem") <= capacity && countOfItem + currentAmountOfItem(this.item, "Gem") <= currentAmountOfItem(this.item, "Gold")) {
            if (!this.item.containsKey("Gem")) {
                this.item.put("Gem", new HashMap<>());
                this.item.get("Gem").put(name, countOfItem);
            } else {
                this.item.get("Gem").put(name, countOfItem + currentAmountOfItem(this.item, name));
            }
            currentCapacity += countOfItem;
        }
    }

    private long currentAmountOfItem(Map<String, Map<String, Long>> item, String name) {
        return item.entrySet().stream().filter(e -> e.getKey().equals(name)).mapToLong(p -> p.getValue().values().stream().mapToLong(x -> x).sum()).sum();
    }

    public Map<String, Map<String, Long>> getItem() {
        return item;
    }

    public void printContent() {
        Map<String, Map<String, Long>> newMap = new LinkedHashMap<>();
        this.item.entrySet()
                .stream()
                .sorted((left, right) -> {
                    long leftSum = left.getValue().values().stream().mapToLong(l -> l).sum();
                    long rightSum = right.getValue().values().stream().mapToLong(l -> l).sum();
                    if (rightSum > leftSum){
                        return 1;
                    }else if (rightSum< leftSum){
                        return -1;
                    }else {
                        return 0;
                    }
                }).forEach(elements -> newMap.put(elements.getKey(), elements.getValue()));
        for (Map.Entry<String, Map<String, Long>> entry : newMap.entrySet()) {
            Long sumValues = entry.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.printf("<%s> $%s%n", entry.getKey(), sumValues);

            entry.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
        }


    }
}



