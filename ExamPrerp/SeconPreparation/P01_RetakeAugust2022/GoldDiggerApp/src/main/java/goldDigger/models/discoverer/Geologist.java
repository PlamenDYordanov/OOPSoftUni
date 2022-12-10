package goldDigger.models.discoverer;

public class Geologist extends BaseDiscoverer{
    private static final double DEFAULT_ENERGY = 100;
    public Geologist(String name) {
        super(name, DEFAULT_ENERGY);
    }
}
