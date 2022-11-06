package Interfaces.Exercise.P06_MilitaryElite.Interfaces;

import Interfaces.Exercise.P06_MilitaryElite.entity.MissionImpl;

import java.util.Collection;

public interface Commando {
    void addMission(MissionImpl mission);
    Collection<MissionImpl> getMissions();

}
