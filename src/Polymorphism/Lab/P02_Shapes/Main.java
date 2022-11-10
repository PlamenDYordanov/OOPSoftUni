package Polymorphism.Lab.P02_Shapes;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(4d);
        circle.calculatePerimeter();
        System.out.println(circle.getPerimeter());
    }
}
