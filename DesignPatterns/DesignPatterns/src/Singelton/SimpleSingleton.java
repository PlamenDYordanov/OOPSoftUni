package Singelton;

public class SimpleSingleton {
    private static final  SimpleSingleton INSTANCE = new SimpleSingleton();

    private SimpleSingleton() {

    }
    public static SimpleSingleton getInstance() {
         return INSTANCE;
    }
    public String printResult() {
        return "Im good";
    }
}
