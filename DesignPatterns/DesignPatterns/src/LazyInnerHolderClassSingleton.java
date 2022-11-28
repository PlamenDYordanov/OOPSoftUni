public class LazyInnerHolderClassSingleton {

    private LazyInnerHolderClassSingleton() {

    }
    private static class  LazyHolder {
        static final  LazyInnerHolderClassSingleton INSTANCE = new LazyInnerHolderClassSingleton();
    }



    public static LazyInnerHolderClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
