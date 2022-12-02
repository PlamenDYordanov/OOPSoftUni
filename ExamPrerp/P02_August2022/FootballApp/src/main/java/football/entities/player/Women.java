package football.entities.player;

public class Women extends BasePlayer{
    private static final double CONSTANT_KG = 85.50;
    public Women(String name, String nationality, int strength) {
        super(name, nationality, CONSTANT_KG, strength);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + 115);
    }
}
