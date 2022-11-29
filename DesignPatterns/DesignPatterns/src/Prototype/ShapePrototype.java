package Prototype;

import java.util.List;

public interface ShapePrototype extends Cloneable{
    ShapePrototype clone()throws CloneNotSupportedException;
    List<String>  getText();
    String getColor();
    void setColor(String color);
}
