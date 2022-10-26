package Encapsulation.Lab.P02_SalaryIncrease;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;


    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public void increaseSalary(double percentage) {
        if (this.age < 30) {
            this.setSalary(getSalary() + (getSalary() * percentage / 200));
        } else {
            this.setSalary(getSalary() + (getSalary() * percentage / 100));
        }
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.0####");
        return String.format("%s %s gets %s leva", getFirstName(), getLastName(), formatter.format(getSalary()));
    }
}
