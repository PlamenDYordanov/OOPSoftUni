package Prototype;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        ShapePrototype firstCircle = new Circle("Green", new ArrayList<>(List.of("Great success!")));

        ShapePrototype secondCircle =  firstCircle.clone();
        secondCircle.getText().add("Im second Object");
        secondCircle.setColor("Red");
        draw(firstCircle);
        draw(secondCircle);
    }



    public static void draw(ShapePrototype shapePrototype) {
        System.out.println(shapePrototype);
        //Draws shape prototype;
    }
}
