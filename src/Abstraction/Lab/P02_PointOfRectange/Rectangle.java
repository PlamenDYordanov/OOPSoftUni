package Abstraction.Lab.P02_PointOfRectange;

public class Rectangle {
    private int bottomLeftX;
    private int bottomLeftY;
    private int topRightX;
    private int topRightY;

    public Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        this.bottomLeftX = bottomLeftX;
        this.bottomLeftY = bottomLeftY;
        this.topRightX = topRightX;
        this.topRightY = topRightY;
    }
    public boolean contains(Point point){
        return point.getX() >= bottomLeftX && point.getX() <= topRightX && point.getY() >= bottomLeftY && point.getY() <= topRightY;
    }
}
