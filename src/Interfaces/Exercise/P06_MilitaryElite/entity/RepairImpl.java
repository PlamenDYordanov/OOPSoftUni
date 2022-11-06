package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Repair;

public class RepairImpl implements Repair {
    private String name;
    private int hours;

    public RepairImpl(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    @Override
    public String getPartName() {
        return name;
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        sb.append("Part Name: ").append(getPartName()).append(" Hours Worked: ").append(getHours());
        return sb.toString();
    }
}
