package Interfaces.Exercise.P06_MilitaryElite.entity;

import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(String firstName, String lastName, int id, String codeNumber) {
        super(firstName, lastName, id);
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCode() {
        return codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %d",getFirstName(), getLastName(),getId()));
        sb.append(System.lineSeparator());
        sb.append("Code Number: ").append(getCode());
        return sb.toString();
    }
}
