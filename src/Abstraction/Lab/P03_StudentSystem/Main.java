package Abstraction.Lab.P03_StudentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();
        String[] input = scanner.nextLine().split(" ");

        while (!input[0].equals("Exit")) {
            String command = input[0];
            String name = input[1];
            switch (command) {
                case "Create":
                    int age = Integer.parseInt(input[2]);
                    double grade = Double.parseDouble(input[3]);
                    Student student = new Student(name, age, grade);
                    studentSystem.addToRepository(student);
                    break;
                case "Show":
                    if (studentSystem.Show(name) != null){
                        System.out.println(studentSystem.Show(name).toString());
                    }
            }
            input = scanner.nextLine().split(" ");
        }
    }
}
