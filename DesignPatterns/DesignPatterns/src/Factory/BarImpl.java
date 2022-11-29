package Factory;

public class BarImpl extends FactoryMethod{

    @Override
    protected AnInterface getIsntace() {
        return new Bar();
    }
}
