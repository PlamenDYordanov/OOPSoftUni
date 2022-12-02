package football.entities.player;

public class Men extends BasePlayer{
    private static final double CONSTANT_KG = 60;
    public Men(String name, String nationality, int strength) {
        super(name, nationality, CONSTANT_KG, strength);
    }
    @Override
    public void stimulation() {
        setStrength(getStrength() + 145);
    }
}
