package Reflection.Exercise.P02_blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();//get empty constructor
        constructor.setAccessible(true);// make it public
        BlackBoxInt blackBoxInt = constructor.newInstance();// create blackBox instance

        Field innerValue = clazz.getDeclaredField("innerValue"); //get metaData for this field "innerValue"
        innerValue.setAccessible(true);

        Scanner scanner = new Scanner(System.in);

        String inputLine = scanner.nextLine();

        while (!inputLine.equals("END")) {
            String commandName = inputLine.split("_")[0];
            int value = Integer.parseInt(inputLine.split("_")[1]);

            Method method = clazz.getDeclaredMethod(commandName, int.class);//get method from blackBoxInt by its name and arguments(signature)
            method.setAccessible(true);// make it public
            method.invoke(blackBoxInt, value);//call the method on the blackBoxInt instance we have
            System.out.println(innerValue.get(blackBoxInt));

            inputLine = scanner.nextLine();
        }

    }
}
