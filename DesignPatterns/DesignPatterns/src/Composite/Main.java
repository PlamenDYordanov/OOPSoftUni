package Composite;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape square = new Square();
        CompositeShape compositeShape = new CompositeShape(circle, square);//when select compositeShape -> select circle and square

        System.out.println(circle.isSelected());
        circle.select();
        System.out.println(circle.isSelected());
    }
}
