package Reflection.Exercise.P01_harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

import static java.lang.reflect.Modifier.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Class clazz = RichSoilLand.class;
		Field[] fields = clazz.getDeclaredFields();


		String inputLine = scanner.nextLine();

		while (!"HARVEST".equals(inputLine)){
			switch (inputLine) {
				case "private":
					Arrays.stream(fields).filter(field -> isPrivate(field.getModifiers()))
							.forEach(getFieldConsumer());
					break;
				case "protected":
					Arrays.stream(fields).filter(field -> isProtected(field.getModifiers()))
							.forEach(getFieldConsumer());
					break;
				case "public":
					Arrays.stream(fields).filter(field -> isPublic(field.getModifiers()))
							.forEach(getFieldConsumer());
					break;
				case "all":
					Arrays.stream(fields).forEach(getFieldConsumer());
					break;
			}

			inputLine = scanner.nextLine();
		}

	}

	private static Consumer<Field> getFieldConsumer() {
		return print -> System.out.printf("%s %s %s%n", Modifier.toString(print.getModifiers()), print.getType().getSimpleName(), print.getName());
	}
}
