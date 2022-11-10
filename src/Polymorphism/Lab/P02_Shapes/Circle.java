package Polymorphism.Lab.P02_Shapes;

public class Circle extends  Shape{
    private Double radius;

    public Circle(Double radius) {

        this.radius = radius;
    }

    @Override
    public void calculatePerimeter() {
        setPerimeter(2 * Math.PI * getRadius());
    }

    @Override
    public void calculateArea() {
       setArea(Math.PI * Math.pow(getRadius(),2));

    }

    public final Double getRadius() {
        return radius;
    }
}
