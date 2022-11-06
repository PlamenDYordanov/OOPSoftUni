package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Enums.Corps;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Engineer;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Repair;

import java.util.ArrayList;
import java.util.Collection;

public class EngineerImpl extends SpecialisedSoldier implements Engineer {
    private Collection<RepairImpl> repairCollection;
    public EngineerImpl(String firstName, String lastName, int id, double salary, Corps corps) {
        super(firstName, lastName, id, salary, corps);
        this.repairCollection = new ArrayList<>();
    }



    @Override
    public void addRepair(RepairImpl repair) {
        this.repairCollection.add(repair);
    }

    @Override
    public Collection<RepairImpl> getRepair() {
        return this.repairCollection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(String.format("%s %s Id: %d Salary: %.2f",getFirstName(), getLastName(), getId(), getSalary()));
        sb.append(System.lineSeparator());
        sb.append("Corps: ").append(getCorps());
        sb.append(System.lineSeparator());
        sb.append("Repairs:");
        for (RepairImpl repair : getRepair()) {
            sb.append(System.lineSeparator());
            sb.append(repair);
        }
        return sb.toString();
    }
}
