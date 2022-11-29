package Factory;

public abstract class FactoryMethod {
    public void doSomethingWithAnInterface() {
        AnInterface instance = getIsntace();
        //do something with instance
    }
    protected  abstract  AnInterface getIsntace();
}
