package zoo.entities.areas;

public class LandArea extends BaseArea {
    private static final int DEFAULT_CAPACITY = 10;

    public LandArea(String name) {
        super(name, DEFAULT_CAPACITY);
    }
}
