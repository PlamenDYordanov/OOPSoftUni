package P03_ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] personsArr = scanner.nextLine().split(";");
        String[] productsArr = scanner.nextLine().split(";");
        List<Person> personList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        Person person = null;
        Product product = null;
        for (String curPerson : personsArr) {
            String name = curPerson.split("=")[0];
            double money = Double.parseDouble(curPerson.split("=")[1]);
            try {
                person = new Person(name, money);
                personList.add(person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        for (String curProduct : productsArr) {
            String name = curProduct.split("=")[0];
            double cost = Double.parseDouble(curProduct.split("=")[1]);
            try {
                product = new Product(name, cost);
                productList.add(product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        String command = scanner.nextLine();
        while (!command.equals("END")) {
            String personWhoBuy = command.split("\\s+")[0];
            String productWitchWantToBuy = command.split("\\s+")[1];

            try {
                boolean isBuy = false;
                for (Person curPerson : personList) {
                    if (curPerson.getName().equals(personWhoBuy)) {
                        for (Product curProduct : productList) {
                            if (curProduct.getName().equals(productWitchWantToBuy)) {
                                curPerson.buyProduct(curProduct);
                                System.out.printf("%s bought %s%n", curPerson.getName(), curProduct.getName());
                                isBuy = true;
                                break;
                            }
                        }
                    }
                    if (isBuy) {
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }


            command = scanner.nextLine();
        }
        for (Person currentPerson : personList) {
            List<String> stringProducts = new ArrayList<>();
            if (currentPerson.getProducts().size() > 0) {
                for (Product currentPersonProduct : currentPerson.getProducts()) {
                    stringProducts.add(currentPersonProduct.getName());
                }
                System.out.println(currentPerson.getName() + " - " + String.join(", ", stringProducts));
            }else {
                System.out.printf("%s - Nothing bought%n", currentPerson.getName());
            }
        }
    }
}
