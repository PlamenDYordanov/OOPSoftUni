package Polymorphism.Lab.P03_Animal;

public class Dog extends  Animal {
    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("I am %s and my favourite food is %s", super.getName(), super.getFavouriteFood())).append("\n").append("DJAAF");
        return sb.toString();
    }
}
