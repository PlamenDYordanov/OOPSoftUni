package Reflection.Lab;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isPublic;

public class P03_HighQualityMistakes {
    public static void main(String[] args) {
        //test
        Class reflection = Reflection.class;
        Field[] fields = reflection.getDeclaredFields();
        Method[] methods = reflection.getDeclaredMethods();
        Arrays.stream(fields).filter(field -> !isPrivate(field.getModifiers()))
                .sorted((left, right) -> {
                    int result = left.getName().compareTo(right.getName());
                    return result;
                }).forEach(print -> System.out.printf("%s must be private!%n", print.getName()));
        /*for (Field field : fields) {
            int modifiers = field.getModifiers();
            boolean flag = !isPrivate(modifiers);
            if (flag) {
                System.out.printf("%s must be private!%n", field.getName());
            }
        }*/
        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("set")){
                int modifiers = method.getModifiers();
                boolean flag = !isPublic(modifiers);
                if (flag){
                    System.out.printf("%s have to be public!%n", method.getName());
                }
            }
        }


    }
}
