package Interfaces.Exercise.P06_MilitaryElite.entity;


import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Soldier;

public class SoldierImpl  implements Soldier {
    private String firstName;
    private String lastName;
    private int id;

    public SoldierImpl(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }


    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public int getId() {
        return id;
    }
}
