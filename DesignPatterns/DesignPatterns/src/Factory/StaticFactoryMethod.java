package Factory;

public class StaticFactoryMethod {
    public static AnInterface createAnInterface() {
        return new Foo();
    }
}
