package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Lieutenant;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Private;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.SpecialSoldier;

import java.util.ArrayList;
import java.util.Collection;

public class LieutenantGeneralImpl extends PrivateImpl implements Lieutenant  {

    private Collection<PrivateImpl> privateCollection;

    public LieutenantGeneralImpl(String firstName, String lastName, int id, double salary) {
        super(firstName, lastName, id, salary);
        this.privateCollection = new ArrayList<>();
    }

    @Override
    public void addPrivate(PrivateImpl priv) {
        privateCollection.add(priv);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(String.format("%s %s Id: %d Salary: %.2f",getFirstName(), getLastName(), getId(), getSalary()));
        sb.append(System.lineSeparator());
        sb.append("Privates:");
        sb.append(System.lineSeparator());
        for (PrivateImpl curPrivate : privateCollection) {
            sb.append("  ");
            sb.append(curPrivate);
        }
        return sb.toString();
    }
}
