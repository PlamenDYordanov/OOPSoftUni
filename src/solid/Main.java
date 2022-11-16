package solid;

import solid.products.Chips;
import solid.products.Chocolate;
import solid.products.Interfaces.Food;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Food chips = new Chips(500);
        Food chocolate = new Chocolate(500);

        List<Food> foodList = new ArrayList<>();
        foodList.add(chips);
        foodList.add(chocolate);



    }
}
