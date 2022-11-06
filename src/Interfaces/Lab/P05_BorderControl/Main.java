package Interfaces.Lab.P05_BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputLine = scanner.nextLine();
        List<Identifiable> identifiableList = new ArrayList<>();

        while (!inputLine.equals("End")){
            String[] inputLineArr = inputLine.split("\\s+");
            String modelOrName = inputLineArr[0];
            if (inputLineArr.length == 2){
                String id = inputLineArr[1];
                identifiableList.add(new Robot(id, modelOrName));

            }else {
                int age = Integer.parseInt(inputLineArr[1]);
                String id = inputLineArr[2];
                identifiableList.add(new Citizen(modelOrName, age, id));
            }


            inputLine = scanner.nextLine();
        }
        String lastDigitFakeId = scanner.nextLine();
        identifiableList.stream().map(Identifiable::getId).filter(id -> id.endsWith(lastDigitFakeId)).forEach(System.out::println);
    }
}
