package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish{
    private static final  int DEFAULT_SIZE = 5;
    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(DEFAULT_SIZE);
    }

    @Override
    public void eat() {
        super.setSize(getSize() + 2);
    }
}
