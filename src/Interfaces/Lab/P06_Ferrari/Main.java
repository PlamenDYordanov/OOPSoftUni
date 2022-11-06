package Interfaces.Lab.P06_Ferrari;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String nameOfDriver = scan.nextLine();
        Ferrari ferrari = new Ferrari(nameOfDriver);
        System.out.println(ferrari);
    }
}
