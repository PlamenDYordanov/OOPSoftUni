package Encapsulation.Exercise.P01_CLassBoxValidation.P02_AnimalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public void setName(String name) {
        if (name == null || name.equals(" ") || name.length() < 1) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0 || age > 15) {
            throw new IllegalArgumentException ("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    public double productPerDay() {
        if (age >= 0 && age <= 5){
            return 2;
        }else if (age >= 6 && age <= 11){
            return 1;
        }
        return 0.75;
    }
    private double calculateProductPerDay() {
        return productPerDay();
    }
    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.", name, age, calculateProductPerDay());
    }
}
