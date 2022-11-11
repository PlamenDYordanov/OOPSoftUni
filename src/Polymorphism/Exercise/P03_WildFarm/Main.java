package Polymorphism.Exercise.P03_WildFarm;


import Polymorphism.Exercise.P03_WildFarm.Animals.*;
import Polymorphism.Exercise.P03_WildFarm.Food.Food;
import Polymorphism.Exercise.P03_WildFarm.Food.Meat;
import Polymorphism.Exercise.P03_WildFarm.Food.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine();
        List<Mammal> mammalList = new ArrayList<>();
        while (!inputData.equals("End")) {
            String[] animalData = inputData.split("\\s+");
            String[] foodData = scanner.nextLine().split("\\s+");

            Mammal mammal = null;
            Food food = null;

            if (foodData[0].equals("Vegetable")) {
                food = new Vegetable(Integer.parseInt(foodData[1]));
            } else {
                food = new Meat(Integer.parseInt(foodData[1]));
            }
            switch (animalData[0]) {
                case "Cat":
                    mammal = new Cat(animalData[1], animalData[0], Integer.parseInt(foodData[1]), Double.parseDouble(animalData[2]), animalData[3], animalData[4]);
                    break;
                case "Tiger":
                    mammal = new Tiger(animalData[1], animalData[0], Integer.parseInt(foodData[1]), Double.parseDouble(animalData[2]), animalData[3]);
                    break;
                case "Zebra":
                    mammal = new Zebra(animalData[1], animalData[0], Integer.parseInt(foodData[1]), Double.parseDouble(animalData[2]), animalData[3]);
                    break;
                case "Mouse":
                    mammal = new Mouse(animalData[1], animalData[0], Integer.parseInt(foodData[1]), Double.parseDouble(animalData[2]), animalData[3]);
                    break;
            }
            mammal.makeSound();
            mammal.eat(food);
            mammalList.add(mammal);
            inputData = scanner.nextLine();
        }
        for (Mammal mammal : mammalList) {
            System.out.println(mammal);
        }

    }
}
