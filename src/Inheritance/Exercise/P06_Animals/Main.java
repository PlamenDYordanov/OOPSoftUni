/*
package animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String animalInfo = scanner.nextLine();

        while (!animalInfo.equals("Beast!")){
                String[] info = scanner.nextLine().split("\\s+");
                String name = info[0];
                int age = Integer.parseInt(info[1]);
            try {
                switch (animalInfo){
                    case "Cat":
                        String gender = info[2];
                        Cat cat = new Cat(name, age, gender);
                        System.out.print(cat);
                        break;
                    case "Dog":
                        gender = info[2];
                        Dog dog = new Dog(name, age, gender);
                        System.out.print(dog);
                        break;
                    case "Frog":
                         gender = info[2];
                         Frog frog = new Frog(name, age, gender);
                        System.out.print(frog);
                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(name, age);
                        System.out.print(kitten);
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(name, age);
                        System.out.print(tomcat);
                        break;
                }

            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            animalInfo = scanner.nextLine();
        }
    }
}
*/
