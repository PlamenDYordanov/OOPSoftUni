package Polymorphism.Lab.P02_Shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }


    @Override
    public void calculatePerimeter() {
        setPerimeter( 2 * getHeight() + 2 * getWidth());
    }

    @Override
    public void calculateArea() {
        setArea(getHeight() * getWidth());

    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }
}
