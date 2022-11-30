package FactorySecondExample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       CakeInterface whiteCake = CakeFactory.createCake();
       CakeInterface darCake = CakeFactory.createCake();
       CakeDarkChocolate darkChocolate = new CakeDarkChocolate(41, 21, 2);
    }


}
