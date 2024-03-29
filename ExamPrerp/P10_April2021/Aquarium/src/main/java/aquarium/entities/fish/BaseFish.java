package aquarium.entities.fish;

import static aquarium.common.ExceptionMessages.*;

public abstract class BaseFish implements Fish{
    private String name;
    private String species;
    private int size;
    private double price;

    public BaseFish(String name, String species, double price) {
        this.setName(name);
        this.setSpecies(species);
        this.setPrice(price);
    }


    @Override
    public void setName(String name) {
        if (isItNullOrWhitespace(name)) {
            throw new NullPointerException(FISH_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
    private void setSpecies(String species) {
        if (isItNullOrWhitespace(species)){
            throw new NullPointerException(SPECIES_NAME_NULL_OR_EMPTY);
        }
        this.species = species;
    }
    private void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException(FISH_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }

    @Override
    public void eat() {
        setSize(getSize() + 5);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
    private boolean isItNullOrWhitespace(String text) {
        return text == null || text.trim().isEmpty();
    }
}
