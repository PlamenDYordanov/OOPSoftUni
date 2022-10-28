package P04_PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pizzaData = scanner.nextLine().split("\\s+");
        Pizza pizza = null;
        try {
            pizza = new Pizza(pizzaData[1], Integer.parseInt(pizzaData[2]));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        String[] doughData = scanner.nextLine().split("\\s+");
        try {
            Dough dough = new Dough(doughData[1], doughData[2], Double.parseDouble(doughData[3]));
            pizza.setDough(dough);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String input = scanner.nextLine();


        while (!input.equals("END")) {
            String nameOfTopping = input.split("\\s+")[1];
            double toppingWeigh = Double.parseDouble(input.split("\\s+")[2]);

            try {
                Topping topping = new Topping(nameOfTopping, toppingWeigh);
                pizza.addTopping(topping);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }

            input = scanner.nextLine();
        }
        System.out.printf("%s - %.2f%n", pizza.getName(), pizza.getOverallCalories());

    }
}
