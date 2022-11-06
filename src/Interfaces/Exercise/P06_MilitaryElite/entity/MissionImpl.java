package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Enums.MissionProgress;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Mission;

public class MissionImpl implements Mission {
    private String name;
    private String state;

    public MissionImpl(String name, String state) {
        this.name = name;
        this.state = state;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void completeMission() {
        state = MissionProgress.finished.name();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        sb.append("Code Name: ").append(getName()).append(" State: ").append(getState());
        return sb.toString();
    }
}
