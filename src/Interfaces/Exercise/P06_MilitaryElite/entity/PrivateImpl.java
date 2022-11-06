package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Private;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Soldier;

public class PrivateImpl extends SoldierImpl implements Private {

    private double salary;

    public PrivateImpl(String firstName, String lastName, int id, double salary) {
        super(firstName, lastName, id);
        this.salary = salary;
    }


    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %d Salary: %.2f", getFirstName(), getLastName(), getId(), getSalary() ));
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
