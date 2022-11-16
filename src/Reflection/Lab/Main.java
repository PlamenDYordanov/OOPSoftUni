package Reflection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        Class reflection = Reflection.class;
        System.out.println(reflection);
        Class superClass = reflection.getSuperclass();
        System.out.println(superClass);
        Class[] interfaces = reflection.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }
        Object reflectionObject = reflection.newInstance();
        System.out.println(reflectionObject);
    }
}
