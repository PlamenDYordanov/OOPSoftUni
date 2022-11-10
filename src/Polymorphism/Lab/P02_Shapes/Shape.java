package Polymorphism.Lab.P02_Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;



    public abstract  void calculatePerimeter();
    public abstract  void calculateArea();

    public Double getPerimeter() {
        return perimeter;
    }

    public Double getArea() {
        return area;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
