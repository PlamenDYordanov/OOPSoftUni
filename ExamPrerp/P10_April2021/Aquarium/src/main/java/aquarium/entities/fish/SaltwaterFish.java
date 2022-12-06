package aquarium.entities.fish;

import aquarium.entities.decorations.Decoration;

public class SaltwaterFish extends BaseFish{
    private static final  int DEFAULT_SIZE = 5;
    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(DEFAULT_SIZE);
    }


    @Override
    public void eat() {
      setSize(getSize() + 2);
    }
}
