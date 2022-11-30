package FactorySecondExample;

import java.util.Scanner;

public class CakeFactory {

    public static CakeInterface createCake() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
       CakeInterface cake = null;
        switch (input) {
            case "Dark":
                cake = new CakeDarkChocolate(40, 35, 12);
                cake.prepare();
                cake.bake();
                cake.box();
                break;
            case "WhiteChocolate":
                cake = new CakeWhiteChocolate(41, 45.50, 16);
                cake.prepare();
                cake.bake();
                cake.box();
        }
        return cake;
    }

}
