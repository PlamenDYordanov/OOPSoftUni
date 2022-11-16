
package Reflection.Lab;
import java.lang.reflect.Method;
import java.util.Arrays;
public class P02_GettersSetters {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        //test
        Class reflection = Reflection.class;
        Method[] methods2 = reflection.getDeclaredMethods();
        Arrays.stream(methods2).filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0)
                .sorted((left, right) -> {
                    int result = left.getName().compareTo(right.getName());
                    return result;
                })
                .forEach(print -> System.out.printf("%s will return %s%n", print.getName(), print.getReturnType()));

        Arrays.stream(methods2).filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1)
                .sorted((left, right) -> {
                    int result = left.getName().compareTo(right.getName());
                    return result;
                })
                .forEach(print -> System.out.printf("%s and will set field of class %s%n", print.getName(), Arrays.toString(print.getParameterTypes())));


    }

}


