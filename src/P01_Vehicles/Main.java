package P01_Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] carData = scanner.nextLine().split("\\s+");
        BaseVehicle car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]));
        String[] truckData = scanner.nextLine().split("\\s+");
        BaseVehicle truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]));

        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= numberOfCommands ; i++) {
            String[] commandLine = scanner.nextLine().split("\\s+");

            switch (commandLine[0]){
                case "Drive":
                    if (commandLine[1].equals("Car")){
                        car.driving(Double.parseDouble(commandLine[2]));
                    }else {
                        truck.driving(Double.parseDouble(commandLine[2]));
                    }
                    break;
                case "Refuel":
                    if (commandLine[1].equals("Car")){
                        car.refueling(Double.parseDouble(commandLine[2]));
                    }else {
                        truck.refueling(Double.parseDouble(commandLine[2]));

                    }
                    break;
            }

        }
        System.out.println(car);
        System.out.println(truck);
    }
}
