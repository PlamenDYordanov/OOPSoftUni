package TestInheritance;

public class Employee  extends Student{

    private String idNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String iD) {
        this.idNumber = iD;
    }

    public Employee(String name, int age, int height, String school, String iD) {
        super(name, age, height, school);
        this.idNumber = iD;
    }
}
