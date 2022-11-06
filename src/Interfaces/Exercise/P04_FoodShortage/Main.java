package FoodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Citizen> citizenList = new ArrayList<>();
        List<Rebel> rebelList = new ArrayList<>();
        int numberOfPeople = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfPeople; i++) {
            String[] personData = scanner.nextLine().split("\\s+");
            String name = personData[0];
            int age = Integer.parseInt(personData[1]);
            if (personData.length == 3){
                String group = personData[2];
                rebelList.add(new Rebel(name, age, group));
            }else {
                String id = personData[2];
                String birthDate = personData[3];
                citizenList.add(new Citizen(name, age, id, birthDate));

            }
        }
        int totalBuyFood = 0;
        String name = scanner.nextLine();
        while (!name.equals("End")){
            for (Citizen citizen : citizenList) {
                if (citizen.getName().equals(name)){
                    citizen.buyFood();
                    totalBuyFood += 10;
                }

            }

            for (Rebel rebel : rebelList) {
                if (rebel.getName().equals(name)){
                    rebel.buyFood();
                    totalBuyFood += 5;
                }
            }
            name = scanner.nextLine();
        }
        System.out.println(totalBuyFood);

    }
}
