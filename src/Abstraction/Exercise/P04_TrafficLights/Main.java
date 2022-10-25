package Abstraction.Exercise.P04_TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] startingLights = scanner.nextLine().split("\\s+");
        int cycle = Integer.parseInt(scanner.nextLine());
        List<Light> trafficLight = new ArrayList<>();
        for (String light : startingLights) {
            Light currentLight = new Light(Color.valueOf(light));
            trafficLight.add(currentLight);
        }
        for (int i = 0; i < cycle; i++) {
            trafficLight.forEach(print -> {
                        print.changeLight();
                        System.out.print(print.getColor() + " ");
                    });
            System.out.println();
        }
    }
}
