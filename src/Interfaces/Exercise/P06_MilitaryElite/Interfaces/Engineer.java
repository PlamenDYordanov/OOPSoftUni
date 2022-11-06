package Interfaces.Exercise.P06_MilitaryElite.Interfaces;

import Interfaces.Exercise.P06_MilitaryElite.entity.RepairImpl;

import java.util.Collection;

public interface Engineer {
    void addRepair(RepairImpl repair);
    Collection<RepairImpl> getRepair();
}
