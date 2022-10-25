package Abstraction.Lab;

import java.util.Scanner;

public class P01_Rhombus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        //upper part of rhombus
        int space = 1;
        upperPartOfRhombus(size, space);
        middlePartOfRhombus(size);
        downPartOfRhombus(size, space);
    }

    private static void downPartOfRhombus(int size, int space) {
        space = 0;
        for (int row = 1; row < size; row++) {


            for (int spaces = 0; spaces <= space; spaces++) {
                System.out.print(" ");
            }
            space++;
            for (int rowStars = space; rowStars < size; rowStars++) {
                System.out.print("*" + " ");
            }
            System.out.println();
        }
    }

    private static void middlePartOfRhombus(int size) {
        for (int i = 1; i <= size; i++) {
            System.out.print("*" + " ");
        }
        System.out.println();
    }

    private static void upperPartOfRhombus(int size, int space) {
        for (int rows = 1; rows < size; rows++) {

            for (int spaces = space; spaces < size; spaces++) {
                System.out.print(" ");
            }
            space++;

            for (int rowStars = 1; rowStars < space; rowStars++) {
                System.out.print("*" + " ");
            }
            System.out.println();
        }
    }
}
