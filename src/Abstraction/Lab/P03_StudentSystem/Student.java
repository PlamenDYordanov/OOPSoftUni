package Abstraction.Lab.P03_StudentSystem;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getGrade() {
        return this.grade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s is %d years old. ", getName(), getAge(), getGrade()));
        if (getGrade() >= 5.00){
            sb.append("Excellent student.");
        }else if (getGrade() < 5.00 && getGrade() >=3.50){
            sb.append("Average student.");
        }else {
            sb.append("Very nice person.");
        }
        return sb.toString();
    }
}
