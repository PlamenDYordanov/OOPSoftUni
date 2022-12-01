package goldDigger.models.discoverer;

public class Archaeologist extends BaseDiscoverer {
    private static final int ENERGY = 60;

    public Archaeologist(String name) {
        super(name, ENERGY);
    }
}
