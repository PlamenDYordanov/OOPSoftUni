package FactorySecondExample;

import java.util.Scanner;

public class CakeFactory {

    public static Cake createCake() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Cake cake = null;
        switch (input) {
            case "carrot":
                cake = new Cake(40, 35, 12);
                cake.prepare();
                cake.bake();
                cake.box();
                break;
            case "fruits":
                cake = new Cake(41, 45.50, 16);
                cake.prepare();
                cake.bake();
                cake.box();
        }
        return cake;
    }

}
