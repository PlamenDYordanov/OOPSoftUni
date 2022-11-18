/*
package P03_BirthdayCelebration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        List<Birthable> objectWithBirthday = new ArrayList<>();

        while (!inputLine.equals("End")) {
            String[] inputData = inputLine.split("\\s+");
            String modelOrName = inputData[1];
            switch (inputData[0]) {
                case "Citizen": {
                    int age = Integer.parseInt(inputData[2]);
                    String id = inputData[3];
                    String birthData = inputData[4];
                    objectWithBirthday.add(new Citizen(modelOrName, age, id, birthData));
                }
                break;
                case "Pet":
                    String birthData = inputData[2];
                    objectWithBirthday.add(new Pet(modelOrName, birthData));
                    break;
                case "Robot":
                    String id = inputData[2];
                    break;
            }


            inputLine = scanner.nextLine();
        }
        String checkYear = scanner.nextLine();
        boolean isExistYear = false;
        for (Birthable currentObject : objectWithBirthday) {
            if (currentObject.getBirthDate().endsWith(checkYear)){
                isExistYear = true;
                System.out.println(currentObject.getBirthDate());
            }
        }

      if (!isExistYear) {
          System.out.println("<no output>");
      }

    }
}
*/
