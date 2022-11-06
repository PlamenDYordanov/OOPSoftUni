package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Enums.Corps;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Private;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.SpecialSoldier;

public class SpecialisedSoldier extends PrivateImpl implements SpecialSoldier {

    private Corps corps;

    public SpecialisedSoldier(String firstName, String lastName, int id, double salary, Corps corps) {
        super(firstName, lastName, id, salary);
        this.corps = corps;
    }


    @Override
    public String getCorps() {
        return corps.name();
    }
}
