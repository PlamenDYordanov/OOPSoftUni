package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Enums.Corps;
import Interfaces.Exercise.P06_MilitaryElite.Enums.MissionProgress;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Commando;


import java.util.ArrayList;
import java.util.Collection;

public class CommandoImpl extends  EngineerImpl implements Commando {
    private MissionProgress missionProgress;
    private Collection<MissionImpl> missionCollection;
    public CommandoImpl(String firstName, String lastName, int id, double salary, Corps corps) {
        super(firstName, lastName, id, salary, corps);
        this.missionCollection = new ArrayList<>();
    }

    @Override
    public void addMission(MissionImpl mission) {
        this.missionCollection.add(mission);
    }

    @Override
    public Collection<MissionImpl> getMissions() {
        return this.missionCollection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %d Salary: %.2f", getFirstName(), getLastName(), getId(), getSalary()));
        sb.append(System.lineSeparator());
        sb.append("Corps: ").append(getCorps());
        sb.append(System.lineSeparator());
        sb.append("Missions:");
        sb.append(System.lineSeparator());
        for (MissionImpl mission : getMissions()) {
            sb.append(mission.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
