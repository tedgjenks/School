package edu.jenks.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {
	
	public static Object newInstance(String fullyQualifiedName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> c = Class.forName(fullyQualifiedName);
		return c.newInstance();
	}
	
	public static Object newInstance(String fullyQualifiedName, Class<?>[] constructorTypes, Object[] constructorArgs) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = Class.forName(fullyQualifiedName);
		Constructor<?> constructor = c.getDeclaredConstructor(constructorTypes);
		return constructor.newInstance(constructorArgs);
	}
}
