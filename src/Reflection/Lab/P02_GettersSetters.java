package Reflection.Lab;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class P02_GettersSetters {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        Class reflection = Reflection.class;

        Method[] methods = reflection.getDeclaredMethods();

       Arrays.stream(methods).filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0)
                .sorted((left, right) -> {
                    int result = left.getName().compareTo(right.getName());
                    return result;
                })
                .forEach(print -> System.out.printf("%s will return %s%n", print.getName(), print.getReturnType()));

        Arrays.stream(methods).filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1)
                .sorted((left, right) -> {
                    int result = left.getName().compareTo(right.getName());
                    return result;
                })
                .forEach(print -> System.out.printf("%s and will set field of class %s%n", print.getName(), Arrays.toString(print.getParameterTypes())));
    }

}

