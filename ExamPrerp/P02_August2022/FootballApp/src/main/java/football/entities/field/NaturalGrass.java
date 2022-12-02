package football.entities.field;

public class NaturalGrass extends BaseField{
    private static final int CONSTANT_CAPACITY = 250;
    public NaturalGrass(String name) {
        super(name, CONSTANT_CAPACITY);
    }
}
