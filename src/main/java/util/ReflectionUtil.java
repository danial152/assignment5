package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static void inspectClass(Object obj) {

        Class<?> cl = obj.getClass();

        System.out.println("reflection\n");
        System.out.println("Class Name: " + cl.getName());

        System.out.println("\n fields:");
        for (Field field : cl.getDeclaredFields()) {
            System.out.println("- " + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("\n methods:");
        for (Method method : cl.getDeclaredMethods()) {
            System.out.println("- " + method.getName());
        }


    }
}
