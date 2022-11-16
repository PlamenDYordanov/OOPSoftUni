package solid;

import java.util.List;

public class Printer {


    public void printCalories (String format, double amount){
        System.out.printf(format, amount);
    }
    public void printTotalWeight(String format,double weight) {

    }
    public void printAverageWeight(String format, double average) {
        System.out.printf("Average: %s%n");
    }
}
