package FactorySecondExample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       Cake cake = CakeFactory.createCake();
    }


}
