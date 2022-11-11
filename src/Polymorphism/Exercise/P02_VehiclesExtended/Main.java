package Polymorphism.Exercise.P02_VehiclesExtended;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] carData = scanner.nextLine().split("\\s+");
        BaseVehicle car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]), Double.parseDouble(carData[3]));
        String[] truckData = scanner.nextLine().split("\\s+");
        BaseVehicle truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]),Double.parseDouble(truckData[3]));
        String[] busData = scanner.nextLine().split("\\s+");
        BaseVehicle bus = new Bus(Double.parseDouble(busData[1]), Double.parseDouble(busData[2]),Double.parseDouble(busData[3]));

        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= numberOfCommands; i++) {
            String[] commandLine = scanner.nextLine().split("\\s+");

            switch (commandLine[0]){
                case "Drive":
                    if (commandLine[1].equals("Car")){
                        car.driving(Double.parseDouble(commandLine[2]));
                    }else if (commandLine[1].equals("Truck")){
                        truck.driving(Double.parseDouble(commandLine[2]));
                    }else {
                        bus.setEmpty(false);
                        bus.driving(Double.parseDouble(commandLine[2]));
                        bus.setEmpty(true);
                    }
                    break;
                case "Refuel":
                    if (commandLine[1].equals("Car")){
                        car.refueling(Double.parseDouble(commandLine[2]));
                    }else if (commandLine[1].equals("Truck")){
                        truck.refueling(Double.parseDouble(commandLine[2]));

                    }else {
                        bus.refueling(Double.parseDouble(commandLine[2]));
                    }
                    break;
                case "DriveEmpty":
                    bus.driving(Double.parseDouble(commandLine[2]));
                    break;
            }

        }
        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }
}
