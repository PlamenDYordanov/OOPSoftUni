package TestInheritance;

public class Student extends Person{
    private String school;

    public Student(String name, int age, int height, String school) {
        super(name, age, height);
        this.school = school;
    }

    public String getSchool() {
        return school;
    }
}
